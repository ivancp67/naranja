package ceu.dam.ad.service;

import java.util.List;

import ceu.dam.ad.dto.request.FilterDto;
import ceu.dam.ad.model.Student;


public interface StudentService {
	
	
	public Student create(Student student) throws StudentDuplicateException;

	
	public void remove(Long id) throws StudentNotFoundException;

	
	public Student findById(Long id) throws StudentNotFoundException;

	
	public List<Student> findAll() throws StudentNotFoundException;


	public List<Student> findByAgeRange(Integer minAge, Integer maxAge);

	
	public List<Student> search(FilterDto filter);
}
