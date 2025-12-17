package ceu.dam.ad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "question_types")
@Data
@Entity
public class QuestionType {

	@Id
	private String code;
	private String description;

}
