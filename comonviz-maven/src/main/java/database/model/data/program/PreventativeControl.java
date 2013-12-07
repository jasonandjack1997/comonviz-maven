package database.model.data.program;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.businessProcessManagement.ProcessRule;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class PreventativeControl extends ControlIdentificationAndDefinition	{


	public PreventativeControl(String name){
		super(name);
	}
	public PreventativeControl() {

	}
	
	
}

