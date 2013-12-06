package database.model.data.program;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import database.model.data.relatedEntity.Administrator;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ObligationIdentificationAndAssessment extends Program	{
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@Enumerated(EnumType.ORDINAL) 
	private Priority priority;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	@Enumerated(EnumType.ORDINAL) 
	private Status status;
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Administrator adminsteredBy;
	
	private String changes;
	private Date changesDate;
	
	public String getChanges() {
		return changes;
	}



	public void setChanges(String changes) {
		this.changes = changes;
	}



	public Date getChangesDate() {
		return changesDate;
	}



	public void setChangesDate(Date changesDate) {
		this.changesDate = changesDate;
	}



	private enum Status{
		OK, Other
	}
	
	private enum Priority{
		High, Medium, Low
	}
	
	

	public ObligationIdentificationAndAssessment(String name){
		super(name);
	}



	public Priority getPriority() {
		return priority;
	}



	public void setPriority(Priority priority) {
		this.priority = priority;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public Administrator getAdminsteredBy() {
		return adminsteredBy;
	}



	public void setAdminsteredBy(Administrator adminsteredBy) {
		this.adminsteredBy = adminsteredBy;
	}

}
