package ceu.dam.ad.api;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.dto.request.PostreRequestDto;
import ceu.dam.ad.dto.response.PostreResponseDto;
import controller.Postre;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/postre")
@SecurityRequirement(name = "ApiKeyAuthentication")
public class PostresController {
	
	/*
	@Autowired
	private ModelMapper mapper;
	*/
	
	@GetMapping("{id}")
	public Postre getById(@PathVariable Long id) {
		return new Postre(id, "chocolate", new BigDecimal(300), new BigDecimal(200), "Tarta Choco", false);
	}
	
	@PostMapping()
	public PostreResponseDto create(@RequestBody PostreRequestDto postreDto) {
		// Obtenemos entity desde RequestDto
		Postre postreEntity = new ModelMapper().map(postreDto, Postre.class);
		// Llamar al servicio para insertar pasando el entity
		postreEntity.setId(756L);
		//Obtenemos ResponseDto desde entity
		return new ModelMapper().map(postreEntity, PostreResponseDto.class);
	}
	
	@GetMapping()
	public List<Postre> search(@RequestParam(required = false) String sabor,
			@RequestParam(required = false) String nombre) {
		List<Postre> lista = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			lista.add( new Postre(i+100L, sabor, new BigDecimal(400), new BigDecimal(300), nombre, false));
		}
		return lista;
	}
	
	@DeleteMapping("{id}")
	public String deleteById(@PathVariable Long id) {
		return "ID: " + id + ", se ha eliminado";
	}
	
	@PutMapping("/{id}")
	public Postre update(@PathVariable Long id, @RequestBody Postre postre) {
		System.out.println("Postre con ID " + postre.getId() + " actualizado");
		postre.setId(id);
		return postre;
	}
}
