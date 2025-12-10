package ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Data
@JsonRootName("libros")
public class Libros {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("libro")
	private List<Libro> libros;


}
