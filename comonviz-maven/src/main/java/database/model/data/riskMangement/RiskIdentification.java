package database.model.data.riskMangement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskIdentification extends RiskAssessment {
	public RiskIdentification(String name) {
		super(name);
	}

}
