package database.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ToolFeature extends BasicRecord	{
	public ToolFeature(String name){
		super(name);
	}
	public ToolFeature() {

	}
	
}
