package ceu.dam.ad.xml.ejercicio10.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonRootName(value = "libros")
public class Libros {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("libro")
	private List<Libro> libros;

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	

}
