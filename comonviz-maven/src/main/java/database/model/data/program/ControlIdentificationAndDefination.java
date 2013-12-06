package database.model.data.program;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.businessProcessManagement.ProcessRule;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlIdentificationAndDefination extends ControlAndMonitoring	{
	@ManyToMany( cascade = {CascadeType.PERSIST} )
	private Set<ProcessRule> processRules = new BasicRecordSet<ProcessRule>();

	public ControlIdentificationAndDefination(String name){
		super(name);
	}

	public Set<ProcessRule> getProcessRules() {
		return processRules;
	}

	public void setProcessRules(Set<ProcessRule> processRules) {
		this.processRules = processRules;
	}

}
