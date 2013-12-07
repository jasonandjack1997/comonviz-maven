package database.model.data.cultureManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CultureMeasurement extends CultureManagement	{
	public CultureMeasurement(String name){
		super(name);
	}
	public CultureMeasurement() {

	}
	
}
