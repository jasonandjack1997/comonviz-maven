package database.model.data.bussinesProcessManagement;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import database.model.data.DataModel;


@Entity
public class ProcessActivity extends DataModel {
	public ProcessActivity() {
	}

	@OneToMany (mappedBy = "activity")
	Set<ProcessObjective> objectives;


	public Set<ProcessObjective> getObjectives() {
		return objectives;
	}

	public void setObjectives(Set<ProcessObjective> objectives) {
		this.objectives = objectives;
	}
	

}
