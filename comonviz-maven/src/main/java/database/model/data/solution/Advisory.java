package database.model.data.solution;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import database.model.data.relatedEntity.Consultant;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Advisory extends Service	{

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Consultant consultant;
	public Advisory(String name){
		super(name);
	}

}
