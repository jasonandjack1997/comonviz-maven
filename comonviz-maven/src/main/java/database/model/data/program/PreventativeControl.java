package database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class PreventativeControl extends ControlIdentificationAndDefination	{
	public PreventativeControl(String name){
		super(name);
	}
}

