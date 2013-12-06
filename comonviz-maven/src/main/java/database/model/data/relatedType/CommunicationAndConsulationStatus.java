package database.model.data.relatedType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CommunicationAndConsulationStatus extends Status{
	public CommunicationAndConsulationStatus() {

	}
	
}
