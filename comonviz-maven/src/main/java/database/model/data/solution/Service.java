package database.model.data.solution;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import database.model.data.BasicRecordSet;
import database.model.data.obligation.Obligation;
import database.model.data.program.Role;
import database.model.data.relatedEntity.ServiceStatus;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Service extends Solution	{
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Obligation> associatedObligations = new BasicRecordSet<Obligation>();
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> associatedRoles= new BasicRecordSet<Role>();
	
	@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private ServiceStatus status;
	
	public Service(String name){
		super(name);
	}

}
