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
	
	
	public ComplianceTask getAssociatedComplianceTask() {
		return associatedComplianceTask;
	}

	public void setAssociatedComplianceTask(ComplianceTask associatedComplianceTask) {
		this.associatedComplianceTask = associatedComplianceTask;
	}

	public Set<ToolFeature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<ToolFeature> features) {
		this.features = features;
	}

	public Tool(String name){
		super(name);
	}
	
	public Tool() {

	}
	

}
