package database.model.data.resource;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecord;
import database.model.data.BasicRecordSet;
import database.model.data.businessProcessManagement.BusinessProcess;
import database.model.data.program.Role;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class NonMonetaryResource extends Resource	{

	private String obligation;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<BusinessProcess> associatedProcess = new BasicRecordSet<BusinessProcess>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> affectedRoles = new BasicRecordSet<Role>();

	
	public NonMonetaryResource(String name){
		super(name);
	}

}
