package database.model.data.bussinesProcessManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import database.model.data.BasicRecord;

@Entity
public class ProcessObjective extends BasicRecord {

	@ManyToOne
	public ProcessActivity processActivity;
	
	@ManyToOne
	public ProcessActivity processRule;

	public ProcessObjective(){
		
	}

	public ProcessObjective(String name){
		super(name);
	}

	public ProcessActivity getProcessActivity() {
		return processActivity;
	}

	public void setProcessActivity(ProcessActivity activity) {
		this.processActivity = activity;
	}

	public ProcessActivity getProcessRule() {
		return processRule;
	}

	public void setProcessRule(ProcessActivity rule) {
		this.processRule = rule;
	}

}
