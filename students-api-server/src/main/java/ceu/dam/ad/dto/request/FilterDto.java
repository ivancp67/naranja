package ceu.dam.ad.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FilterDto {

	private String dni;
	private String firstName;
	private String lastName;
	@Email
	private String email;
	private LocalDate birthday;
	private String gender;
	private String program;
	@Positive
	private Integer age;
}
