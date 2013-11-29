package database.model.data.bussinesProcessManagement;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import database.model.data.DataModel;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class ProcessRule extends DataModel {
	
	private int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int compareTo(ProcessRule o) {
		return super.compareTo(o);
	}

}
