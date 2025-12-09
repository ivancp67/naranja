package ceu.dam.ad.repository.perros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.model.perros.Perro;
import java.util.List;
import java.util.Optional;


@Repository
public interface PerrosRepository extends JpaRepository<Perro, Long>{
	
}
