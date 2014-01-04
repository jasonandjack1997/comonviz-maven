package database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlAndMonitoring extends Program	{
	public ControlAndMonitoring(String name){
		super(name);
	}

	public ControlAndMonitoring() {

	}
	
}
