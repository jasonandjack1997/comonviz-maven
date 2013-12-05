package database.model.data.businessProcessManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import database.model.data.BasicRecord;
import database.model.data.BasicRecordSet;
import database.model.data.City;


@Entity
public class ProcessActivity extends BusinessProcess {
	
	public ProcessActivity(){
		
	}
	
	public ProcessActivity(String name){
		super(name);
	}

    @ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ProcessObjective> processObjectives =  new BasicRecordSet<ProcessObjective>();



	@ManyToOne
	public ProcessRule processRule;


	@ManyToOne
	private ProcessMonitoring processMonitoring;

	@ManyToOne
	private ProcessMonitoring processImprovement;
	
	
	public Set<ProcessObjective> getProcessObjectives() {
		return processObjectives;
	}

	public void setProcessObjectives(Set<ProcessObjective> objectives) {
		this.processObjectives = objectives;
	}

	public ProcessMonitoring getProcessMonitoring() {
		return processMonitoring;
	}

	public void setProcessMonitoring(ProcessMonitoring processMonitoring) {
		this.processMonitoring = processMonitoring;
	}
	public ProcessRule getProcessRule() {
		return processRule;
	}

	public void setProcessRule(ProcessRule rule) {
		this.processRule = rule;
	}

	public ProcessMonitoring getProcessImprovement() {
		return processImprovement;
	}

	public void setProcessImprovement(ProcessMonitoring processImprovement) {
		this.processImprovement = processImprovement;
	}
	
	

}
