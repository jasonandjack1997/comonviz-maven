package database.model.data.riskManagement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import database.model.data.BasicRecordSet;
import database.model.data.businessProcessManagement.BusinessProcess;
import database.model.data.obligation.Obligation;
import database.model.data.program.Stakeholder;
import database.model.data.relatedEntity.Reference;
import database.model.data.relatedEntity.RiskCriterion;
import database.model.data.relatedType.RiskEvaluationStatus;
import database.model.data.relatedType.RiskPossibilityOfOccurence;
import database.model.data.relatedType.RiskPossibilityToControl;
import database.model.data.relatedType.RiskType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskAssessment extends RiskManagementProcess {
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskType riskType;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Stakeholder> affectedStakeholders = new BasicRecordSet<Stakeholder>();
	
	public RiskType getRiskType() {
		return riskType;
	}
	public void setRiskType(RiskType riskType) {
		this.riskType = riskType;
	}
	public Set<Stakeholder> getAffectedStakeholders() {
		return affectedStakeholders;
	}
	public void setAffectedStakeholders(Set<Stakeholder> affectedStakeholders) {
		this.affectedStakeholders = affectedStakeholders;
	}
	public Set<BusinessProcess> getAffectedProcesses() {
		return affectedProcesses;
	}
	public void setAffectedProcesses(Set<BusinessProcess> affectedProcesses) {
		this.affectedProcesses = affectedProcesses;
	}
	public Set<Obligation> getAssociatedObligations() {
		return associatedObligations;
	}
	public void setAssociatedObligations(Set<Obligation> associatedObligations) {
		this.associatedObligations = associatedObligations;
	}
	public RiskPossibilityOfOccurence getPossibilityOfOccurence() {
		return possibilityOfOccurence;
	}
	public void setPossibilityOfOccurence(
			RiskPossibilityOfOccurence possibilityOfOccurence) {
		this.possibilityOfOccurence = possibilityOfOccurence;
	}
	public String getAssociatedDamage() {
		return associatedDamage;
	}
	public void setAssociatedDamage(String associatedDamage) {
		this.associatedDamage = associatedDamage;
	}
	public RiskPossibilityToControl getPossibilityToControl() {
		return possibilityToControl;
	}
	public void setPossibilityToControl(
			RiskPossibilityToControl possibilityToControl) {
		this.possibilityToControl = possibilityToControl;
	}
	public Set<RiskCriterion> getRiskCriteria() {
		return riskCriteria;
	}
	public void setRiskCriteria(Set<RiskCriterion> riskCriteria) {
		this.riskCriteria = riskCriteria;
	}
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	public RiskEvaluationStatus getStatus() {
		return status;
	}
	public void setStatus(RiskEvaluationStatus status) {
		this.status = status;
	}
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> affectedProcesses= new BasicRecordSet<BusinessProcess>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Obligation> associatedObligations= new BasicRecordSet<Obligation>();


	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskPossibilityOfOccurence possibilityOfOccurence;
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskPossibilityToControl possibilityToControl;

	private String associatedDamage;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskCriterion> riskCriteria = new BasicRecordSet<RiskCriterion>();
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Reference reference;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskEvaluationStatus status;

	
	public RiskAssessment(String name) {
		super(name);
	}
	public RiskAssessment() {

	}
	

}
