package ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.service;

import java.io.File;

import java.util.List;

import ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.modelo.Libro;
import ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.modelo.Libros;
import ceu.dam.ad.ejerciciosTema2.xml.exceptions.XMLExportException;
import tools.jackson.dataformat.xml.XmlMapper;

public class Ejercicio10ServiceImpl implements Ejercicio10Service{

	@Override
	public void escribirXMLLibros(String pathFile, List<Libro> lista) throws XMLExportException {
		XmlMapper xmlMapper = new XmlMapper();
		Libros librosObject = new Libros();
		librosObject.setLibros(lista);
		File file = new File(pathFile);
		xmlMapper.writeValue(file, librosObject);
	}

	@Override
	public List<Libro> leerXMLLibros(String pathFile) throws XMLExportException {
		return null;
	}

	

}
