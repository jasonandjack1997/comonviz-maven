package database.model.data.program;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Role extends Structure	{
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Program> associcatedActivities = new BasicRecordSet<Program>();
	
	private String obligation;
	
	public Role(String name){
		super(name);
	}

}
