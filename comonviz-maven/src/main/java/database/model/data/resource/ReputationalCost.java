package database.model.data.resource;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import database.model.data.relatedType.DamageType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ReputationalCost extends NonMonetaryResource {

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private DamageType typeOfDamange;
	
	
	public ReputationalCost(String name) {
		super(name);
	}
	public ReputationalCost() {

	}
	

}
