package database.model.data.resource;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class PenaltyCost extends MonetaryResource	{
	public PenaltyCost(String name){
		super(name);
	}

}
