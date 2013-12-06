package database.model.data.program;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.relatedEntity.ExternalMember;
import database.model.data.relatedEntity.InternalMember;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Committee extends Structure	{
	
	private Timestamp establishmentStartTime;
	private Timestamp establishmentEndTime;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<InternalMember> internalMembers = new BasicRecordSet<InternalMember>();

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ExternalMember> externalMembers = new BasicRecordSet<ExternalMember>();
	
	private String obligation;
	
	public Committee(String name){
		super(name);
	}

}
