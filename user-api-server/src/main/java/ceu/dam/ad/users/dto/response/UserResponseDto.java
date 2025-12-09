package ceu.dam.ad.users.dto.response;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class UserResponseDto {
	private Long id;
	private String username;
	private String email;
	private String password;
	private LocalDate createdDate;
	private LocalDate lastLoginDate;
}
