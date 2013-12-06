package database.model.data.relatedEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Beneficiary extends BasicRecord	{
	public Beneficiary(String name){
		super(name);
	}
	public Beneficiary() {

	}
	
}
