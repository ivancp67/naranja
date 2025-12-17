package ceu.dam.ad.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@DiscriminatorValue("SC")
@Entity
public class SingleChoiceQuestion extends Question{

	@Override
	public Boolean validate() {
		Integer count = 0;
		if (getAnswers().size()<2) {
			
		}
		
		return true;
	}

}
