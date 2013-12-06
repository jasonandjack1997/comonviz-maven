package database.model.data.program;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlImplementation extends ControlAndMonitoring	{
	
	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private ControlIdentificationAndDefination control;

	
	
	public ControlImplementation(String name){
		super(name);
	}
	
	private enum Status{
		OK,
		Other
	}

}
