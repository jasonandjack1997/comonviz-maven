package database.model.data.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.program.Program;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class RemediationCost extends MonetaryResource	{
	
	private String nonComplianceEvent;
	private String obligation;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Program> associatedProcesses = new BasicRecordSet<Program>();
	
	public RemediationCost(String name){
		super(name);
	}

}
