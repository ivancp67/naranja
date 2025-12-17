package ceu.dam.ad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.model.Question;

import java.util.List;


@Repository
public interface QuestionRepository  extends JpaRepository<Question, Long>{

	List<Question> findAllByTypeCodeCode(String typeCode);
	
}
