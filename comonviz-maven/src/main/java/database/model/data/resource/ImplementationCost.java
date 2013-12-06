package database.model.data.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import database.model.data.BasicRecordSet;
import database.model.data.program.Program;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ImplementationCost extends MonetaryResource	{

	private enum CostType{
		TypeA,
		TypeB
	}
	
	@Enumerated(EnumType.ORDINAL)
	private CostType costType;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Program> associatedProgram = new BasicRecordSet<Program>();
	
	public ImplementationCost(String name){
		super(name);
	}

}
