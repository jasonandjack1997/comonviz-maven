package database.model.data.solution;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.relatedEntity.ComplianceTask;
import database.model.data.relatedEntity.ToolFeature;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Tool extends Solution	{
	
	private ComplianceTask associatedComplianceTask;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ToolFeature> features = new BasicRecordSet<ToolFeature>();
	
	
	public Tool(String name){
		super(name);
	}

}
