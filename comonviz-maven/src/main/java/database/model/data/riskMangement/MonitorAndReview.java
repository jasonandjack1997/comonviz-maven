package database.model.data.riskMangement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MonitorAndReview extends RiskManagementProcess {
	public MonitorAndReview(String name) {
		super(name);
	}

}
