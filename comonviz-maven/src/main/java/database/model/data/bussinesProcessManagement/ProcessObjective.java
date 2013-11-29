package database.model.data.bussinesProcessManagement;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import database.model.data.DataModel;


@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class ProcessObjective extends DataModel{

	public int compareTo(ProcessActivity o) {
		return super.compareTo(o);
	}

}
