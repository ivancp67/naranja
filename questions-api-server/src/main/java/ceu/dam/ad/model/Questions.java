package ceu.dam.ad.model;

import lombok.Data;

@Data
public abstract class Questions {

	private Integer id;
	private String text;
	private String type_code;

}
