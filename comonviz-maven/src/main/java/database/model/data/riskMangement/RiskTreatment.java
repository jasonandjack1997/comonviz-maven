package database.model.data.riskMangement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskTreatment extends RiskManagementProcess {
	public RiskTreatment(String name) {
		super(name);
	}
	
	public RiskTreatment() {

	}
	

}
