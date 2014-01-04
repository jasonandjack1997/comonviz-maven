package database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Structure extends Program	{
	public Structure(String name){
		super(name);
	}

	public Structure() {

	}
	
}
