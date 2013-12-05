package database.model.data.riskMangement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskEvaluation extends RiskAssessment {
	public RiskEvaluation(String name) {
		super(name);
	}

}
