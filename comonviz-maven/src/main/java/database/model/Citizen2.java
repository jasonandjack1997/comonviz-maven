package database.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import database.model.data.DataModel;


@Entity
public class Citizen2 extends DataModel {
	String job;
	@ManyToOne
	Town2 town;

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Town2 getTown() {
		return town;
	}

	public void setTown(Town2 town) {
		this.town = town;
	}
	
	public Citizen2() {
		int a = 1;
	}

	public Citizen2(String name, String job, Town2 town) {
		this.setName(name);
		this.job = job;
		this.town = town;
	}

}
