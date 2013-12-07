package database.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ExternalAudit extends Audit {

	public ExternalAudit(String name) {
		super(name);
	}

	public ExternalAudit() {

	}

}
