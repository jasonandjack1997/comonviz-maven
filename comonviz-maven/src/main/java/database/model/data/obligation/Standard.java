package database.model.data.obligation;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.relatedEntity.IssuingBody;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Standard extends VoluntaryObligation	{
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<IssuingBody> issuingBodies =  new BasicRecordSet<IssuingBody>();

	public Standard(String name){
		super(name);
	}

	public Set<IssuingBody> getIssuingBodies() {
		return issuingBodies;
	}

	public void setIssuingBodies(Set<IssuingBody> issuingBodies) {
		this.issuingBodies = issuingBodies;
	}

}
