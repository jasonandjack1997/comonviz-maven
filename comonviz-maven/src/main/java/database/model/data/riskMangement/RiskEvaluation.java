package database.model.data.riskMangement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.relatedEntity.Reference;
import database.model.data.relatedEntity.RiskCriterion;
import database.model.data.relatedEntity.RiskEvaluationStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskEvaluation extends RiskAssessment {
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskCriterion> riskCriteria = new BasicRecordSet<RiskCriterion>();
	
	private Reference reference;
	
	private RiskEvaluationStatus status;
	
	
	public RiskEvaluation(String name) {
		super(name);
	}

}
