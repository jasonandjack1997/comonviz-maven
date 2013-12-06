package database.model.data.resource;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ReputationalCost extends NonMonetaryResource {
	private enum DamageType{
		TypeA,
		TypeB
	}
	
	@Enumerated(EnumType.ORDINAL)
	private DamageType typeOfDamange;
	
	
	public ReputationalCost(String name) {
		super(name);
	}

}
