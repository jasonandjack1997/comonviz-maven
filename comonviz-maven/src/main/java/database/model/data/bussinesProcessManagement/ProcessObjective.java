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
	public ProcessActivity activity;
	
	public ProcessObjective(){
		
	}

	public ProcessObjective(String name){
		super(name);
	}

	public ProcessActivity getActivity() {
		return activity;
	}

	public void setActivity(ProcessActivity activity) {
		this.activity = activity;
	}

}
