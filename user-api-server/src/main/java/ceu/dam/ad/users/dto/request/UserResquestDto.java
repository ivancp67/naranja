package ceu.dam.ad.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserResquestDto {
	
	@NotBlank
	private String username;
	@NotBlank
	@Email(message = "El email puesto tiene que ser correcto")
	private String email;
	@NotBlank
	@Size(min = 8, max = 20, message = "La password nueva tiene que tener entre 8 y 20 carácteres")
	private String password;
	
	
}
