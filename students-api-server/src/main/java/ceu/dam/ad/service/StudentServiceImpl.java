package ceu.dam.ad.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ceu.dam.ad.dto.request.FilterDto;
import ceu.dam.ad.model.Student;
import ceu.dam.ad.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepo;
	
	/*
	 * Creará el alumno en BBDD. La fecha de alta es algo que el servicio debe registrar 
	 * automáticamente. Si ya existe alumno con el mismo DNI o Email lanzará un 
	 * StudentDuplicateException. Devolverá el alumno creado con todos sus datos. 
	 */
	@Override
	public Student create(Student student) throws StudentDuplicateException{
		if (!studentRepo.findByDni(student.getDni()).isEmpty()) {
			throw new StudentDuplicateException("Student with this id already exists");
		}
		else if (!studentRepo.findByEmail(student.getEmail()).isEmpty()) {
			throw new StudentDuplicateException("Student with this email already exists");
		}
		student.setCreatedAt(LocalDate.now());
		return studentRepo.save(student);
	}

	/*
	 * Eliminará el alumno con el id indicado en BBDD. Si el alumno no existe, 
	 * lanzará StudentNotFoundException. 
	 */
	@Override
	public void remove(Long id) throws StudentNotFoundException{
		if (studentRepo.findById(id).isEmpty()) {
			throw new StudentNotFoundException("Student with this id doesn't exists");
		}
		studentRepo.deleteById(id);
	}

	/*
	 * Devolverá el alumno con el id indicado. Si no existe, lanzará StudentNotFoundException.
	 */
	@Override
	@GetMapping("/{id}")
	public Student findById(Long id) throws StudentNotFoundException{
		Optional<Student> studentOpt = studentRepo.findById(id);
		if (studentOpt.isEmpty()) {
			throw new StudentNotFoundException("Student with this id doesn't exists");
		}
		return studentOpt.get();
		
	}

	/*
	 * Devolverá todos los alumnos ordenados por dni ascendente. Si no hay ninguno, 
	 * lanzará StudentNotFoundException.
	 */
	@Override
	public List<Student> findAll() throws StudentNotFoundException{
		List<Student> students = studentRepo.findAll(Sort.by("dni").ascending());
		if (students.isEmpty()) {
			throw new StudentNotFoundException("No students found");
		}
		return students;
		
	}

	/*
	 * Devolverá todos los alumnos con edades comprendidas entre la mínima y máxima recibidas 
	 * (ambas inclusive). La lista estará ordenada por edad ascendente. Si no hay ninguno, 
	 * devolverá una lista vacía. 
	 */
	@Override
	public List<Student> findByAgeRange(Integer minAge, Integer maxAge){
		LocalDate dateFrom = LocalDate.now().minusYears(maxAge);
		LocalDate dateUntil = LocalDate.now().minusYears(minAge);
		return studentRepo.findByBirthdayBetween(dateFrom, dateUntil, Sort.by("Birthday"));
		
	}

	@Override
	public List<Student> search(FilterDto filter){
		
		return null;
		
	}
}
