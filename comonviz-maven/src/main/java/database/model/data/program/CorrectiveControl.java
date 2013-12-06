package database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CorrectiveControl extends ControlIdentificationAndDefination	{
	public CorrectiveControl(String name){
		super(name);
	}

	public CorrectiveControl() {

	}
	
}
