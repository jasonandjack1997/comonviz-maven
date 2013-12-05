package database.model.data.businessProcessManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BusinessProcessManagement extends BasicRecord	{
	public BusinessProcessManagement(){
		
	}
	
	public BusinessProcessManagement(String name){
		super(name);
	}

}
