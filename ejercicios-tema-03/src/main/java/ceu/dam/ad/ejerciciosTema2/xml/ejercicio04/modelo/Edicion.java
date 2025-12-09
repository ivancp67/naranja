package ceu.dam.ad.ejerciciosTema2.xml.ejercicio04.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Edicion {
	private Integer año;
	private String editorial;
	
	
	@Override
	public String toString() {
		return "Edicion [año=" + año + ", editorial=" + editorial + "]";
	}
	
	
}
