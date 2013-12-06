package database.model.data.program;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import database.model.data.relatedType.Status;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlImplementation extends ControlAndMonitoring	{
	
	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private ControlIdentificationAndDefination control;
	
	public ControlIdentificationAndDefination getControl() {
		return control;
	}

	public void setControl(ControlIdentificationAndDefination control) {
		this.control = control;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Timestamp getActivationStartTime() {
		return activationStartTime;
	}

	public void setActivationStartTime(Timestamp activationStartTime) {
		this.activationStartTime = activationStartTime;
	}

	public Timestamp getActivateionEndTime() {
		return activateionEndTime;
	}

	public void setActivateionEndTime(Timestamp activateionEndTime) {
		this.activateionEndTime = activateionEndTime;
	}

	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Status status;
	
	private Timestamp activationStartTime;
	private Timestamp activateionEndTime;

	
	
	public ControlImplementation(String name){
		super(name);
	}
	
	public ControlImplementation() {

	}
	

}
