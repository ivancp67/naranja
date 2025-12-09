package ceu.dam.ad.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.model.perros.Perro;
import ceu.dam.ad.model.perros.Persona;
import ceu.dam.ad.repository.perros.PerrosRepository;
import ceu.dam.ad.repository.perros.PersonaRepository;
import jakarta.transaction.Transactional;

@Service
public class PerrosService {

	@Autowired
	private PerrosRepository repoPerro;
	@Autowired
	private PersonaRepository repoPersona;
	
	public void createPersona(Persona persona) {
		repoPersona.save(persona);
	}
	
	
	public Persona consultarPersona(Long id) {
		return repoPersona.findById(id).orElseThrow(()->new RuntimeException("No existe!!"));
	}
	/*
	public Perro createPerro(Perro perro) {
		return repoPerro.save(perro);
	}
	
	@Transactional
	public void createPerros(List<Perro> perros) {
		//perros.forEach(p -> repo.save(p));
		//perros.forEach(p -> createPerro(p));
		repoPerro.saveAll(perros);
	}
	
	
	public Perro consultarPerro(Long id) throws NotFoundException {
		Optional<Perro> optionalPerro = repoPerro.findById(id);
		
		return optionalPerro.orElseThrow(()-> new NotFoundException("No existe el perro"));
		 * Otra forma más sencilla:
		if (optionalPerro.isPresent()) {
			return optionalPerro.get();
		}
		throw new NotFoundException("No existe Perro");
	}
	
	public List<Perro> buscarPerroPorNombre(String filtroNombre){
		return repoPerro.findByPeroneContains(filtroNombre);
	}
	*/
}
