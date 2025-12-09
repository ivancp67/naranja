package ceu.dam.ad.users.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginRequestDto {
	
	@NotEmpty(message = "El username es obligatorio")
	@Schema(description = "Username o email con el que se hará login")
	private String login;
	
	@NotEmpty(message = "El password es obligatorio")
	@Schema(description = "password con el que se hará login")
	private String password;
}
