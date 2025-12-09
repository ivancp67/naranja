package ceu.dam.ad.model.perros;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "perros")
public class Perro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPerro;
	private String nombre;
	private String numChip;
	private String raza;
	private Boolean vacunado;
	
	
	//private Persona propietario;

}
