package database.model.data.bussinesProcessManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import database.model.data.BasicRecord;
import database.model.data.BasicRecordSet;


//@Entity
public class ProcessMonitoring extends BasicRecord {
	public ProcessMonitoring() {
	}
	
	public ProcessMonitoring(String name){
		super(name);
	}

	@OneToMany (mappedBy = "processActivity")
	private Set<ProcessObjective> processObjectives =  new BasicRecordSet<ProcessObjective>();


	public Set<ProcessObjective> getProcessObjectives() {
		return processObjectives;
	}

	public void setProcessObjectives(Set<ProcessObjective> objectives) {
		this.processObjectives = objectives;
	}
	

}
