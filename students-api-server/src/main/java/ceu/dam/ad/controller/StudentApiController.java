package ceu.dam.ad.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.dto.request.FilterDto;
import ceu.dam.ad.dto.request.NewStudentRequestDto;
import ceu.dam.ad.dto.response.StudentResponseDto;
import ceu.dam.ad.model.Student;
import ceu.dam.ad.service.StudentDuplicateException;
import ceu.dam.ad.service.StudentNotFoundException;
import ceu.dam.ad.service.StudentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "ApiKeyAuthentication")
@RestController
@RequestMapping("/student")
public class StudentApiController {

	@Autowired
	private StudentService service;
	
	/*
	 * Creará el alumno en BBDD. La fecha de alta es algo que el servicio debe registrar 
	 * automáticamente. Si ya existe alumno con el mismo DNI o Email lanzará un 
	 * StudentDuplicateException. Devolverá el alumno creado con todos sus datos. 
	 */
	@PostMapping
	public StudentResponseDto create(@RequestBody @Valid NewStudentRequestDto student) throws StudentDuplicateException{
		ModelMapper modelMapper = new ModelMapper();
		Student studentEntity = modelMapper.map(student, Student.class);
		Student studentCreated = service.create(studentEntity);
		return modelMapper.map(studentCreated, StudentResponseDto.class);
		
	}

	/*
	 * Eliminará el alumno con el id indicado en BBDD. Si el alumno no existe, 
	 * lanzará StudentNotFoundException. 
	 */
	@DeleteMapping("/{idStudent}")
	public void remove(@PathVariable Long idStudent) throws StudentNotFoundException{
		service.remove(idStudent);
	}

	/*
	 * Devolverá el alumno con el id indicado. Si no existe, lanzará StudentNotFoundException.
	 */
	@GetMapping("/{idStudent}")
	public StudentResponseDto findById(@PathVariable Long idStudent) throws StudentNotFoundException{
		Student student = service.findById(idStudent);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(student, StudentResponseDto.class);
	}

	/*
	 * Devolverá todos los alumnos ordenados por dni ascendente. Si no hay ninguno, 
	 * lanzará StudentNotFoundException.
	 */
	@GetMapping()
	public List<StudentResponseDto> findAll() throws StudentNotFoundException{
		List<Student> students = service.findAll();
		ModelMapper modelMapper = new ModelMapper();
		return students.stream().map(s -> modelMapper.map(s, StudentResponseDto.class)).toList();
	}

	/*
	 * Devolverá todos los alumnos con edades comprendidas entre la mínima y máxima recibidas 
	 * (ambas inclusive). La lista estará ordenada por edad ascendente. Si no hay ninguno, 
	 * devolverá una lista vacía. 
	 */
	@GetMapping("/age")
	public List<StudentResponseDto> findByAgeRange(@RequestParam(required = false) Integer minAge, @RequestParam(required = false) Integer maxAge){
		if (minAge==null) {
			minAge=0;
		}
		if (maxAge==null) {
			maxAge = Integer.MAX_VALUE;
		}
		List<Student> students = service.findByAgeRange(minAge, maxAge);
		ModelMapper modelMapper = new ModelMapper();
		return students.stream().map(s -> modelMapper.map(s, StudentResponseDto.class)).toList();
	}

	@GetMapping("/search")
	public List<StudentResponseDto> search(@RequestBody @Valid FilterDto filter){
		List<Student> students = service.search(filter);
		ModelMapper modelMapper = new ModelMapper();
		return students.stream().map(s -> modelMapper.map(s, StudentResponseDto.class)).toList();
	}
}
