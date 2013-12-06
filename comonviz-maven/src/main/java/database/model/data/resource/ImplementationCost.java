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
import database.model.data.relatedType.CostType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ImplementationCost extends MonetaryResource	{

	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private CostType costType;
	
	public CostType getCostType() {
		return costType;
	}
	public void setCostType(CostType costType) {
		this.costType = costType;
	}
	public Set<Program> getAssociatedProgram() {
		return associatedProgram;
	}
	public void setAssociatedProgram(Set<Program> associatedProgram) {
		this.associatedProgram = associatedProgram;
	}
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Program> associatedProgram = new BasicRecordSet<Program>();
	
	public ImplementationCost(String name){
		super(name);
	}
	public ImplementationCost() {

	}
	

}
