package database.model.data.solution;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import database.model.data.relatedEntity.Auditor;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Audit extends Service	{
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Auditor auditor;
	
	private Serializable documentID;
	
	public Audit(String name){
		super(name);
	}

}
