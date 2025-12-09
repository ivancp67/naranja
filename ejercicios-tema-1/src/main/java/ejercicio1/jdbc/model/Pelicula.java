package ejercicio1.jdbc.model;

import lombok.Data;

@Data
public class Pelicula {
	private Long id;
	private String titulo;
	private Integer longitud;
	
	public Pelicula() {
		
	}
	
}
