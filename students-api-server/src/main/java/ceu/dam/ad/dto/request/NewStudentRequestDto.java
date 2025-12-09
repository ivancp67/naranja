package ceu.dam.ad.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class NewStudentRequestDto {

	//Falta poner los mensajes
	@NotBlank
	private String dni;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Email
	private String email;
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@PastOrPresent
	private LocalDate birthday;
	private String gender;
	private String program;

}
