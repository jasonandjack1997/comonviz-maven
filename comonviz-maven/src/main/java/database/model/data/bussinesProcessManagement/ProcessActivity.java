package database.model.data.bussinesProcessManagement;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import database.model.data.BasicRecord;
import database.model.data.BasicRecordSet;


@Entity
public class ProcessActivity extends BasicRecord {
	public ProcessActivity() {
	}
	
	public ProcessActivity(String name){
		super(name);
	}

	@OneToMany (mappedBy = "processActivity")
	private Set<ProcessObjective> processObjectives =  new BasicRecordSet<ProcessObjective>();

//	@ManyToOne
//	private ProcessMonitoring processMonitoring;

	public Set<ProcessObjective> getProcessObjectives() {
		return processObjectives;
	}

	public void setProcessObjectives(Set<ProcessObjective> objectives) {
		this.processObjectives = objectives;
	}
	

}
