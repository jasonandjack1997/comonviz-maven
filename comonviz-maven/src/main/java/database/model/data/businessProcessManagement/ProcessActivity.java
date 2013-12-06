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
public class ProcessActivity extends BasicRecord {
	
	public ProcessActivity(){
		
	}
	
	public ProcessActivity(String name){
		super(name);
	}

    @ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ProcessObjective> processObjectives =  new BasicRecordSet<ProcessObjective>();


	public Set<ProcessObjective> getProcessObjectives() {
		return processObjectives;
	}

	public void setProcessObjectives(Set<ProcessObjective> objectives) {
		this.processObjectives = objectives;
	}


}
