package database.model.data.program;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import database.model.data.BasicRecordSet;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Review extends Program {
	private String changes;
	private Timestamp date;

	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<Role> affectedRoles = new BasicRecordSet<Role>();
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private Committee committee;

	public Review(String name) {
		super(name);
	}

}
