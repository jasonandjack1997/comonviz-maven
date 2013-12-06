package database.model.data.riskMangement;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import database.model.data.BasicRecordSet;
import database.model.data.program.Role;
import database.model.data.relatedEntity.CommunicationAndConsulationStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CommunicationAndConsultation extends RiskManagementProcess {
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<RiskAssessment> associatedRisks = new BasicRecordSet<RiskAssessment>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> targetedRoles = new BasicRecordSet<Role>();
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private CommunicationAndConsulationStatus status;
	

	public CommunicationAndConsultation(String name) {
		super(name);
	}

}
