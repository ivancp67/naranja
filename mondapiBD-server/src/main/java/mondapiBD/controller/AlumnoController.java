package mondapiBD.controller;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import mondapiBD.dto.request.RegistroPracticaRequest;
import mondapiBD.dto.response.AlumnoResponse;
import mondapiBD.exception.ConflictException;
import mondapiBD.exception.NotFoundException;
import mondapiBD.exception.NotValidException;
import mondapiBD.model.Alumno;
import mondapiBD.model.RegistroPractica;
import mondapiBD.service.AlumnoService;
import mondapiBD.service.PracticasService;

@RestController
@RequestMapping("/mondapi/alumno")
@SecurityRequirement(name = "Authorization")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private PracticasService practicasService;

	@Operation(summary = "Ver perfil del alumno", description = "Muestra datos y resumen de horas. Oculta contacto del tutor laboral [12]")
	@GetMapping("/{id}/perfil")
	public ResponseEntity<AlumnoResponse> verPerfil(@PathVariable String id) throws NotFoundException {
		// El servicio ya limpia los campos sensibles de la empresa [12]
		Map<String, Object> perfilCompleto = alumnoService.obtenerPerfilCompleto(id);

		// Usamos ModelMapper para convertir el Alumno del mapa en el DTO de respuesta
		Alumno alumno = (Alumno) perfilCompleto.get("alumno");
		AlumnoResponse response = new ModelMapper().map(alumno, AlumnoResponse.class);

		// Enriquecemos el DTO con los datos del resumen calculados en el servicio [12]
		Map<String, Object> resumen = alumnoService.obtenerResumenHoras(id);
		response.setHorasTotales((Double) resumen.get("horasTotales"));
		response.setHorasRealizadas((Double) resumen.get("horasRealizadas"));
		response.setHorasPendientes((Double) resumen.get("horasPendientes"));
		response.setPorcentajeCompletado((Double) resumen.get("porcentaje"));

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "Registrar tareas diarias", description = "Crea un registro de horas. Máximo 8h y saltos de 0.5h [7]")
	@PostMapping("/registro")
	public ResponseEntity<RegistroPractica> crearRegistro(@Valid @RequestBody RegistroPracticaRequest dto,
			@RequestParam String alumnoId) throws ConflictException, NotValidException {
		// Convertimos el DTO a modelo antes de pasarlo al servicio
		RegistroPractica registro = new ModelMapper().map(dto, RegistroPractica.class);
		registro.setIdAlumno(alumnoId);

		return ResponseEntity.status(HttpStatus.CREATED).body(practicasService.crearRegistro(registro));
	}

	@Operation(summary = "Listar mis registros", description = "Muestra todas las tareas realizadas por el alumno [13]")
	@GetMapping("/{id}/registros")
	public List<RegistroPractica> listarRegistros(@PathVariable String id) {
		return practicasService.listarRegistrosPorAlumno(id);
	}
}
