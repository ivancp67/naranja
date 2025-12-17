package ceu.dam.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.model.QuestionType;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, String>{

	
	
}
