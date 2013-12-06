package database.model.data.program;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import database.model.data.relatedType.StakeholderType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Stakeholder extends Structure	{
	
	@ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
	private StakeholderType stakeholderType;
	
	private String obligation;
	
	public Stakeholder(String name){
		super(name);
	}
	public Stakeholder() {

	}
	

}
