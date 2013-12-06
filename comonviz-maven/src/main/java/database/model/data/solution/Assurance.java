package database.model.data.solution;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import database.model.data.relatedEntity.AssuranceRequiredCondition;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Assurance extends Service	{
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private AssuranceRequiredCondition requiredCondition;
	
	public Assurance(String name){
		super(name);
	}

}
