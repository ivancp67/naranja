package ceu.dam.ad.users.services;

import java.sql.Connection;

import org.apache.commons.codec.digest.DigestUtils;

import ceu.dam.ad.users.dao.UserDao;
import ceu.dam.ad.users.model.User;

public class UserServiceImpl extends Service implements UserService {

	private UserDao dao;
	
	public UserServiceImpl() {
		dao = new UserDao();
	}

	
	@Override
	public User createUser(User user) throws DuplicateUserException, UserException {
		try (Connection conn = abrirConexionSakila()){
			conn.setAutoCommit(false);
			User u1 = dao.getByEmail(conn, user.getEmail());
			User u2 = dao.getByUserName(conn, user.getUsername());
			if(user.getEmail().equals(u1.getEmail()) 
					|| user.getEmail().equals(u2.getUsername())) {
				throw new DuplicateUserException("Ese usuario ya existe");
			}
			conn.commit();
			return user;
		} catch (Exception e) {
			throw new UserException("Error creando al usuario", e);
		}
	}
	
	/** Recibe el id de un usuario, el password antiguo y el nuevo. Los dos sin cifrar. El servicio tendrá que:
	 * 1. Si el usuario no existe con ese ID, lanzar UserNotFoundException
	 * 2. Verificar que la nueva password no es igual a la antigua. Si lo es, lanzar UserUnauthorizedException
	 * 3. Verificar que la password antigua es correcta. Si no lo es, lanzar UserUnauthorizedException
	 * 4. Actualizar el nuevo password en el usuario cifrándolo previamente.
	 * 5. Si hay algún error, lanzará UserException con el origen
	 */
	@Override
	public void changePassword(Long idUser, String oldPassword, String newPassword)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		try (Connection conn = abrirConexionSakila()){
			conn.setAutoCommit(false);
			String oldPasswordCif = DigestUtils.sha3_512Hex(oldPassword);
			String newPasswordCif = DigestUtils.sha3_512Hex(newPassword);
			User user = dao.getById(conn, idUser);
			if (!idUser.equals(user.getId())) {
				throw new UserNotFoundException("Ese usuario no existe");
			}
			else if (oldPasswordCif.equals(newPasswordCif)) {
				throw new UserUnauthorizedException("Las contraseñas no son iguales");
			}
			else if (!oldPasswordCif.equals(user.getPassword())) {
				throw new UserUnauthorizedException("La contraseña antigua no coincide");
			}
			conn.commit();
		} catch (Exception e) {
			throw new UserException("Error cambiando la contraseña", e);
		}
	}
	
	/** Recibe un login que puede ser un username o un email, y el password sin cifrar. El servicio tendrá que:
	 * 1. Verificar que existe algún usuario con ese username o email. Si no es así, lanzar UserNotFoundException
	 * 2. Verificar que password es correcta. Si lo es, lanzar UserUnauthorizedException
	 * 3. Actualizamos fecha del último login. Si hay algún error aquí, registramos en el log, pero continuamos.
	 * 4. Devolver el usuario con todos sus datos que ha realizado el login.
	 * 5. Si hay algún error, lanzará UserException con el origen
	 */
	@Override
	public User login(String login, String password)
			throws UserNotFoundException, UserUnauthorizedException, UserException {
		try (Connection conn = abrirConexionSakila()){
			User u1 = dao.getByEmail(conn, login);
			if (u1 == null) {
				u1 = dao.getByUserName(conn, login);
			}
			if (!login.equals(u1.getEmail()) || !login.equals(u1.getUsername())) {
				throw new UserNotFoundException("Ese usuario no existe");
			}
			else if (!password.equals(u1.getPassword())) {
				throw new UserUnauthorizedException("La contraseña no es correcta");
			}
		} catch (Exception e) {
			throw new UserException("Error haciendo login", e);
		}
		
		return null;
	
	}
	
	/** Recibe el id de un usuario. El servicio tendrá que:
	 * 1. Si el usuario no existe con ese ID, lanzar UserNotFoundException
	 * 2. Devolver los datos completos del usuario
	 * 3. Si hay algún error, lanzará UserException con el origen
	 */
	@Override
	public User getUser(Long idUser) throws UserNotFoundException, UserException {
		try (Connection conn = abrirConexionSakila()){
			User user = dao.getById(conn, idUser);
			if (!idUser.equals(user.getId())) {
				throw new UserNotFoundException("Ese usuario no existe");
			}
			return user;
		} catch (Exception e) {
			throw new UserException("Error haciendo login", e);
		}
	}

}
