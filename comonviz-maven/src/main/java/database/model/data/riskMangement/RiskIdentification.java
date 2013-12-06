package database.model.data.riskMangement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.businessProcessManagement.BusinessProcess;
import database.model.data.obligation.Obligation;
import database.model.data.program.Stakeholder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskIdentification extends RiskAssessment {
	
	private enum RiskType{
		A,
		B
	}
	
	private RiskType riskType;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Stakeholder> affectedStakeholders = new BasicRecordSet<Stakeholder>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> affectedProcesses= new BasicRecordSet<BusinessProcess>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Obligation> associatedObligations= new BasicRecordSet<Obligation>();
	
	public RiskIdentification(String name) {
		super(name);
	}

}
