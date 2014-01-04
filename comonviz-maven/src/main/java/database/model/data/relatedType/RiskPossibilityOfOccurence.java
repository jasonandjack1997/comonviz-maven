package database.model.data.relatedType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RiskPossibilityOfOccurence extends BasicRecord{

	public RiskPossibilityOfOccurence(String name) {
		super(name);
	}

	public RiskPossibilityOfOccurence() {
	}
}
