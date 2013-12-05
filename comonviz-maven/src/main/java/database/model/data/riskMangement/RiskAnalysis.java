package database.model.data.riskMangement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskAnalysis extends RiskAssessment {
	public RiskAnalysis(String name) {
		super(name);
	}

}
