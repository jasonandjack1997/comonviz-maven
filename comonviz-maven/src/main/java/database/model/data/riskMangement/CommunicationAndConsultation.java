package database.model.data.riskMangement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CommunicationAndConsultation extends RiskManagementProcess {
	public CommunicationAndConsultation(String name) {
		super(name);
	}

}
