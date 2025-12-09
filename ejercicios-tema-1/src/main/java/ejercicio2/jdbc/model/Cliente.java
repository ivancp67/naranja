package ejercicio2.jdbc.model;

import lombok.Data;

@Data
public class Cliente {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean activo;
	
}
