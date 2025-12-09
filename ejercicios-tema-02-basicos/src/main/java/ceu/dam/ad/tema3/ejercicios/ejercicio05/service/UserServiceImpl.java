package ceu.dam.ad.tema3.ejercicios.ejercicio05.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import ceu.dam.ad.tema3.ejercicios.ejercicio05.model.User;
import ceu.dam.ad.tema3.ejercicios.ejercicio05.repository.UserDao;
import ceu.dam.ad.tema3.ejercicios.ejercicio05.repository.UsersRepository;

public class UserServiceImpl  implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UsersRepository repo;

	@Override
	public User createUser(User user) throws DuplicateUserException, UserException {
		log.debug("Creando nuevo usuario: " + user);
		try {
			// 1. Comprobar si existe usuario con ese email o username
			User existsUser = repo.save(user);
			if (existsUser != null) {
				log.debug("Usuario repetido");
				throw new DuplicateUserException("Ya existe usuario indicado");
			}
			// 2. Cifrar password y poner fecha alta
			String passwordCifrada = DigestUtils.sha256Hex(user.getPassword());
			user.setPassword(passwordCifrada);
			user.setCreatedDate(LocalDate.now());

			// 3. Insertar usuario
			Long id = user.getId();

			// 4. Recoger id creado y devolver user completo
			user.setId(id);
			log.debug("Usuario creado con éxito con id " + id);
			return user;
		} catch (DataAccessException e) {
			log.error("Error creando usuario ", e);
			throw new UserException("Error registrando usuario");
		}
	}

	@Override
	public void changePassword(Long idUser, String oldPassword, String newPassword)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		log.debug("Actualizacion password de usuario con id: " + idUser);
		try {
			// 0. Comprobar que password sean diferentes
			if (newPassword.equals(oldPassword)) {
				log.debug("Pass antigua igual a la nueva, no se hará el cambio ");
				throw new UserUnauthorizedException("La password nueva no puede ser igual a la antigua");
			}

			// 1. Comprobar si usuario existe
			Optional<User> optUser = repo.findById(idUser);
			if (optUser.isEmpty()) {
				log.warn("El usuario indicado no existe. ID " + idUser);
				throw new UserNotFoundException("No existe usuario con id " + idUser);
			}
			
			// 2. Comprobamos password antigua
			String passwordCipherOld = DigestUtils.sha256Hex(oldPassword);
			if (!optUser.get().equals(passwordCipherOld)) {
				log.debug("Pass indicada para cambio incorrecta ");
				throw new UserUnauthorizedException("El password no es correcto");
			}
			
			String passwordCipherNew = DigestUtils.sha256Hex(newPassword);
			optUser.get().setPassword(passwordCipherNew);
			repo.save(optUser.get());
			log.debug("Password cambiada con exito");
			
		} catch (DataAccessException e) {
			log.error("Error actualizando pass de usuario ", e);
			throw new UserException("Error actualizando usuario");
		}
	}

	@Override
	public User login(String login, String password)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		log.debug("Realizando login con usuario " + login);
		try {
			// 1. Comprobar si existe login como username o como email
			log.debug("Intentando login por email...");
			Optional<User> user = repo.findByEmail(login);
			if (user == null) {
				log.debug("Intentando login por username...");
				user = repo.findAll();
			}
			if (user == null) {
				log.debug("No existe usuario (email o username)");
				throw new UserNotFoundException("No existe usuario con el login indicado");
			}
			
			// 2. Comprobar password cifrándola previamente
			String passwordCipher = DigestUtils.sha256Hex(password);
			if (!user.equals(passwordCipher)) {
				log.debug("Password incorrecta");
				throw new UserUnauthorizedException("Password de usuario incorrecta");
			}
			
			// 3. Actualizamos fecha último login
			try {
				log.debug("Actualizando fecha de último login");
				user.setLastLoginDate(LocalDate.now());
				dao.update(conn, user);
			}
			catch(SQLException e) {
				log.error("Error actualizando fecha último login del usuario ", e);
			}
			log.debug("Login correcto");
			return user;
			
		}catch (SQLException e) {
			log.error("Error actualizando pass de usuario ", e);
			throw new UserException("Error actualizando usuario");
		}
		
	}

	@Override
	public User getUser(Long idUser) throws UserNotFoundException, UserException {
		log.debug("Consultando usuario con id " + idUser);
		try (Connection conn = null) {
			User user = dao.getById(conn, idUser);
			if (user == null) {
				throw new UserNotFoundException("No existe usuario con el id indicado");
			}
			return user;
		}catch (SQLException e) {
			log.error("Error actualizando pass de usuario ", e);
			throw new UserException("Error actualizando usuario");
		}
	}

}
