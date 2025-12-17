package ceu.dam.ad.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("MC")
@Entity
public class MultipleChoiceQuestion extends Question{

	@Override
	public Boolean validate() {
		return true;
	}

}
