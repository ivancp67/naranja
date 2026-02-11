package mondapiBD.controller;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mondapiBD.dto.response.EmpresaResponse;
import mondapiBD.dto.response.UsuarioResponse;
import mondapiBD.exception.ConflictException;
import mondapiBD.model.Empresa;
import mondapiBD.model.Usuario;
import mondapiBD.model.enums.Evaluacion;
import mondapiBD.service.AdminService;
import mondapiBD.service.PracticasService;

@RestController
@RequestMapping("/mondapi/admin")
@SecurityRequirement(name = "Authorization")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private PracticasService practicasService;

	@Operation(summary = "Listar empresas", description = "Devuelve empresas ordenadas alfabéticamente [9]")
	@GetMapping("/empresas")
	public List<EmpresaResponse> listarEmpresas() {
		List<Empresa> empresas = adminService.listarEmpresas();

		// Mapeo de lista instanciando ModelMapper en el stream [2]
		return empresas.stream().map(e -> new ModelMapper().map(e, EmpresaResponse.class)).toList();
	}

	@Operation(summary = "Generar calendario de prácticas", description = "Registra días laborables excluyendo fines de semana [8]")
	@PostMapping("/calendario/generar")
	public ResponseEntity<String> generarCalendario(
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate inicio,
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fin, @RequestParam Integer año,
			@RequestParam Evaluacion eval) throws ConflictException {
		practicasService.generarCalendario(inicio, fin, año, eval);
		return ResponseEntity.status(HttpStatus.CREATED).body("Calendario generado correctamente");
	}

	@Operation(summary = "Crear nuevo usuario", description = "Crea un acceso asociado a alumno o tutor [10]")
	@PostMapping("/usuarios")
	public UsuarioResponse crearUsuario(@Valid @RequestBody Usuario usuarioModel) throws ConflictException {
		Usuario nuevo = adminService.crearUsuario(usuarioModel);
		return new ModelMapper().map(nuevo, UsuarioResponse.class);
	}
}
