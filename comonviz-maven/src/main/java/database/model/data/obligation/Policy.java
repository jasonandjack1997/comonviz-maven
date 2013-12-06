package database.model.data.obligation;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.model.data.relatedEntity.GoverningBody;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Policy extends MandatoryObligation {
	private String versionNumber;
	

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<GoverningBody> governingBodies =  new BasicRecordSet<GoverningBody>();

	public Policy(String name) {
		super(name);
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Set<GoverningBody> getGoverningBodies() {
		return governingBodies;
	}

	public void setGoverningBodies(Set<GoverningBody> governingBodies) {
		this.governingBodies = governingBodies;
	}
	public Policy() {

	}
	
}
