package database.model.data.program;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class RecordKeepingAndReporting extends Program {

	private String coverage;
	private Timestamp lodgementStartTime;
	private Timestamp lodgementEndTime;
	private String submissionTo;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> affectedRoles = new BasicRecordSet<Role>();

	public RecordKeepingAndReporting(String name) {
		super(name);
	}

}
