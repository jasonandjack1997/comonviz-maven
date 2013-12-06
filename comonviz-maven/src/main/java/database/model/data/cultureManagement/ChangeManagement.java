package database.model.data.cultureManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ChangeManagement extends ComplianceDriver {

	private String timeLine;
	private String targetGroup;

	public ChangeManagement(String name) {
		super(name);
	}

	public String getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(String timeLine) {
		this.timeLine = timeLine;
	}

	public String getTargetGroup() {
		return targetGroup;
	}

}
