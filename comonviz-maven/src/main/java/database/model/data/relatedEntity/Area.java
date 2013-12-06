package database.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Area extends BasicRecord	{
	public Area(String name){
		super(name);
	}
	public Area() {

	}
	
}
