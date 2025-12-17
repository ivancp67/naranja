package ceu.dam.ad.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("TF")
@Entity
public class TrueFalseQuestion extends Question{

	@Override
	public Boolean validate() {
		return true;
	}

	

}
