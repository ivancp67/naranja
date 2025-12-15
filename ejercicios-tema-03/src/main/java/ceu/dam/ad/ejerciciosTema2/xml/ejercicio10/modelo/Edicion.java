package ceu.dam.ad.ejerciciosTema2.xml.ejercicio10.modelo;


import lombok.Data;

@Data
public class Edicion {
	private Integer año;
	private String editorial;

	public Edicion(Integer año, String editorial) {
		super();
		this.año = año;
		this.editorial = editorial;
	}
	

	@Override
	public String toString() {
		return "Edicion [año=" + año + ", editorial=" + editorial + "]";
	}
	
	
}
