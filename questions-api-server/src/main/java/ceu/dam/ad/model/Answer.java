package ceu.dam.ad.model;

import lombok.Data;

@Data
public class Answer {

	private Integer id;
	private Integer question_id;
	private String text;
	private Boolean correct;


}
