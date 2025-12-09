package ceu.dam.ad.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.model.Student;
import java.time.LocalDate;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public List<Student> findByEmail(String email);
	
	public List<Student> findByDni(String dni);

	public List<Student> findByBirthdayBetween(LocalDate dateFrom, LocalDate dateUntil, Sort sort);
	
}
