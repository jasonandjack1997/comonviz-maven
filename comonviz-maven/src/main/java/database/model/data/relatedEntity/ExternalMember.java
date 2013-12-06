package database.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ExternalMember extends Member	{
	public ExternalMember(String name){
		super(name);
	}
	public ExternalMember() {

	}
	
}
