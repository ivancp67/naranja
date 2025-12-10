package ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.test;

import java.util.ArrayList;
import java.util.List;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.modelo.Edicion;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.modelo.Libro;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.service.Ejercicio10Service;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.service.Ejercicio10ServiceImpl;

public class Test {
	public static void main(String[] args) {
		Ejercicio10Service service = new Ejercicio10ServiceImpl();
		try {
			List<Libro> randomList = Libro.createRandomList(5);
			service.escribirXMLLibros("c:/temporal/libros.xml", randomList);
			
		} catch (Exception e) {
			System.err.println("Error al generar el XML: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
}


