package mondapiBD.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mondapiBD.dto.request.LoginRequest;
import mondapiBD.dto.request.PasswordChangeRequest;
import mondapiBD.dto.response.UsuarioResponse;
import mondapiBD.exception.InactiveUserException;
import mondapiBD.exception.IncorrectPasswordException;
import mondapiBD.exception.NotFoundException;
import mondapiBD.model.Usuario;
import mondapiBD.service.SecurityService;

@RestController
@RequestMapping("/mondapi/auth")
@SecurityRequirement(name = "Authorization") // Documentación de seguridad [5]
public class AuthController {

	@Autowired
	private SecurityService securityService;

	@Operation(summary = "Login de usuario", description = "Valida credenciales y devuelve el perfil del usuario")
	@PostMapping("/login")
	public ResponseEntity<UsuarioResponse> login(@Valid @RequestBody LoginRequest loginDto) throws NotFoundException, InactiveUserException, IncorrectPasswordException {
		// El servicio valida el HASH y si está activo [6, 7]
		Usuario usuario = securityService.login(loginDto.getUsername(), loginDto.getPassword());

		// Conversión manual con ModelMapper instanciado cada vez [1]
		UsuarioResponse response = new ModelMapper().map(usuario, UsuarioResponse.class);
		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Cambiar contraseña", description = "Actualiza la contraseña validando longitud mínima")
	@PutMapping("/password/{id}")
	public ResponseEntity<Void> cambiarPassword(@PathVariable String id,
			@Valid @RequestBody PasswordChangeRequest dto) throws NotFoundException {
		securityService.actualizarPassword(id, dto.getNuevaPassword());
		return ResponseEntity.noContent().build();
	}
}
