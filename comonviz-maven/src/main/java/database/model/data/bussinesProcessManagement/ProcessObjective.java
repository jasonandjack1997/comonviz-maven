package database.model.data.bussinesProcessManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import database.model.data.DataModel;

@Entity
public class ProcessObjective extends DataModel {

	@ManyToOne
	ProcessActivity activity;

	public ProcessObjective() {
	}

	public ProcessActivity getActivity() {
		return activity;
	}

	public void setActivity(ProcessActivity activity) {
		this.activity = activity;
	}

}
