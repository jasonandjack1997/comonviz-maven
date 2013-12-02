package database.model.data.bussinesProcessManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import database.model.BasicRecordSet;
import database.model.data.BasicRecord;


@Entity
public class ProcessActivity extends BasicRecord {
	public ProcessActivity() {
	}
	
	public ProcessActivity(String name){
		super(name);
	}

	@OneToMany (mappedBy = "activity")
	@OnDelete(action=OnDeleteAction.CASCADE)
	public Set<ProcessObjective> objectives =  new BasicRecordSet<ProcessObjective>();


	public Set<ProcessObjective> getObjectives() {
		return objectives;
	}

	public void setObjectives(Set<ProcessObjective> objectives) {
		this.objectives = objectives;
	}
	

}
