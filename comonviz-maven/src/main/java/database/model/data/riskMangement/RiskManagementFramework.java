package database.model.data.riskMangement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;
import database.model.data.obligation.Obligation;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskManagementFramework extends RiskManagement {
	public RiskManagementFramework(String name) {
		super(name);
	}

}
