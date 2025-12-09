package ceu.dam.ad.tema3.ejercicios.ejercicio05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ceu.dam.ad.tema3.ejercicios.ejercicio05.model.User;
import java.util.Optional;



public interface UsersRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmail(String email);
}
