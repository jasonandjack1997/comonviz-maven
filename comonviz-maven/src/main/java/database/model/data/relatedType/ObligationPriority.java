package database.model.data.relatedType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ObligationPriority extends BasicRecord	{
	public ObligationPriority(String name){
		super(name);
	}
	public ObligationPriority() {

	}
	
}
