package database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlAssessmentAndMaintenance extends ControlAndMonitoring	{
	
	@Enumerated(EnumType.ORDINAL)
	private AssessmentType assessmentType;
	
	
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	private String maintenanceTask;
	
	private enum Status{
		OK,
		Other
	}
	
	
	private enum AssessmentType{
		A,
		B,
		C
	}
	public ControlAssessmentAndMaintenance(String name){
		super(name);
	}

}
