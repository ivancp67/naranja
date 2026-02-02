package ceu.dam.ad.dto.response;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StudentResponseDto {

	private Long id;
	private String dni;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthday;
	private String gender;
	private String program;
	private LocalDate createdAt;

}
