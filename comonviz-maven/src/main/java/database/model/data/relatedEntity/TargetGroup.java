package database.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class TargetGroup extends BasicRecord	{
	public TargetGroup(String name){
		super(name);
	}
	public TargetGroup() {

	}
	
}
