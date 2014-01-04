package database.model.data.relatedType;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class DamageType extends BasicRecord	{
	public DamageType(String name){
		super(name);
	}
	public DamageType() {

	}
	
}
