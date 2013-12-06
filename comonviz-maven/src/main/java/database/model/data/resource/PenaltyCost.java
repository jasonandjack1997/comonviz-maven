package database.model.data.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.relatedEntity.Beneficiary;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class PenaltyCost extends MonetaryResource	{

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Beneficiary> beneficiaries = new BasicRecordSet<Beneficiary>();
	
	public PenaltyCost(String name){
		super(name);
	}
	public PenaltyCost() {

	}
	

}
