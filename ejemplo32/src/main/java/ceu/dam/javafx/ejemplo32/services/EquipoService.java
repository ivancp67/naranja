package ceu.dam.javafx.ejemplo32.services;

import java.util.ArrayList;
import java.util.List;

import ceu.dam.javafx.ejemplo32.modelo.Equipo;

public class EquipoService {

	private static List<Equipo> equipos = new ArrayList();

	static {
		equipos.add(new Equipo("Sevilla", 1908, "Sevilla"));
		equipos.add(new Equipo("Betis", 1912, "Sevilla"));
		equipos.add(new Equipo("Cádiz", 1928, "Cádiz"));
	}
		
	public List<Equipo> consultarEquipos(){
		return equipos;
	}
	
	public List<Equipo> consultarEquiposFiltrado(String filtro, String ciudad){


		List<Equipo> equiposFiltrado = new ArrayList<>();
		for(Equipo equipo:equipos) {
			if(equipo.getNombre().startsWith(filtro) || equipo.getCiudad().equals(ciudad))
				equiposFiltrado.add(equipo);
		}
		
		return equiposFiltrado;
	}
	
	public List<Equipo> consultarEquiposFiltradoCiudad(String ciudad) throws InterruptedException{

		Thread.sleep(5000);
		List<Equipo> equiposFiltrado = new ArrayList<>();
		for(Equipo equipo:equipos) {
			if(equipo.getCiudad().equals(ciudad))
				equiposFiltrado.add(equipo);
		}
		
		return equiposFiltrado;
	}
	
	public void anyadirEquipo(String nombre, Integer anyo, String ciudad) {
		Equipo equipo = new Equipo(nombre, anyo, ciudad);
		equipos.add(equipo);
	}
}
