package ceu.dam.ad.users.dto.response;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserResponseDto {
	
	@NotEmpty(message = "El usuario no puede ser vacío")
	@Size(min = 8, message = "El usuario tiene que tener un mínimo de 8 carácteres")
	private String username;
	
	@NotEmpty(message = "El email no puede ser vacío")
	@Size(min = 8, message = "El email tiene que tener un mínimo de 8 carácteres")
	@Email(message = "Tiene que ser un email")
	private String email;
	
	@NotEmpty(message = "El password no puede ser vacío")
	@Size(min = 8, message = "El password tiene que tener un mínimo de 8 carácteres")
	private String password;
	private LocalDate createdDate;
}
