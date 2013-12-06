package database.model.data.riskMangement;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import database.model.data.BasicRecordSet;
import database.model.data.businessProcessManagement.BusinessProcess;
import database.model.data.program.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class MonitorAndReview extends RiskManagementProcess {
	
	private Timestamp dateTime;
	
	
	
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private RiskAssessment risk;
	
	private String reviewDecision;
	private String action;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> affectedProcesses = new BasicRecordSet<BusinessProcess>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> affectedRoles = new BasicRecordSet<Role>();

	public MonitorAndReview(String name) {
		super(name);
	}

}
