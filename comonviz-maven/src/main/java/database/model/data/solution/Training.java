package database.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Training extends Advisory	{
	public Training(String name){
		super(name);
	}

	public Training() {

	}
	
}
