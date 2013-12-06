package database.model.data.relatedType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class RiskEvaluationStatus extends Status{

public RiskEvaluationStatus(String name) {
	super(name);
}

public RiskEvaluationStatus() {
}
	
}
