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
import database.model.data.relatedEntity.Reference;
import database.model.data.relatedEntity.RiskCriterion;
import database.model.data.relatedEntity.RiskEvaluationStatus;
import database.model.data.relatedEntity.RiskPossibilityOfOccurence;
import database.model.data.relatedEntity.RiskPossibilityToControl;
import database.model.data.relatedEntity.RiskType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskAssessment extends RiskManagementProcess {
	
	private RiskType riskType;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Stakeholder> affectedStakeholders = new BasicRecordSet<Stakeholder>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> affectedProcesses= new BasicRecordSet<BusinessProcess>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Obligation> associatedObligations= new BasicRecordSet<Obligation>();


	private RiskPossibilityOfOccurence possibilityOfOccurence;
	
	private String associatedDamage;
	
	private RiskPossibilityToControl possibilityToControl;

	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskCriterion> riskCriteria = new BasicRecordSet<RiskCriterion>();
	
	private Reference reference;
	
	private RiskEvaluationStatus status;

	
	public RiskAssessment(String name) {
		super(name);
	}

}
