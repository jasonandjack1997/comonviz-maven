package database.model.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.Trackable;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class DataModel extends Trackable implements Comparable<DataModel>{

	@Column(columnDefinition = "TEXT")
	private String discription = "";

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public int compareTo(DataModel o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
