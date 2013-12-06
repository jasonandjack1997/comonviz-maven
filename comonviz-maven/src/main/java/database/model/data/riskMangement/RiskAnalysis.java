package database.model.data.riskMangement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskAnalysis extends RiskAssessment {

	private enum PossibilityOfOccurence{
		High,
		Medium,
		Low
	}
	
	private enum PossibilityToControl{
		High,
		Medium,
		Low
	}

	private PossibilityOfOccurence possibilityOfOccurence;
	
	private String associatedDamage;
	
	private PossibilityToControl possibilityToControl;
	
	public RiskAnalysis(String name) {
		super(name);
	}

}
