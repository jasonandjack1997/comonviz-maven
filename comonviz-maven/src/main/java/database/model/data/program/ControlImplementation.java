package database.model.data.program;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlImplementation extends ControlAndMonitoring	{
	
	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private ControlIdentificationAndDefination control;
	
	private Status status;
	
	private Timestamp activationStartTime;
	private Timestamp activateionEndTime;

	
	
	public ControlImplementation(String name){
		super(name);
	}
	
	private enum Status{
		OK,
		Other
	}

}
