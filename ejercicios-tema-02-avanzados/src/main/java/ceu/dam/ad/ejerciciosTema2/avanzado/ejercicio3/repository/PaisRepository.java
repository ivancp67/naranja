package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Pais;
import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<Pais, String>{

	public List<Pais> findByDescripcionStartingWith(String descripcion);
	
}
