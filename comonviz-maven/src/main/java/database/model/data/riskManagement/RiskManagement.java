package database.model.data.riskManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;
import database.model.data.obligation.Obligation;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskManagement extends BasicRecord {
	public RiskManagement(String name) {
		super(name);
	}
	public RiskManagement() {

	}
	

}
