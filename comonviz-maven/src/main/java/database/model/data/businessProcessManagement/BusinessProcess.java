package database.model.data.businessProcessManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BusinessProcess extends BusinessProcessManagement	{
	
	public BusinessProcess(){
		
	}
	
	public BusinessProcess(String name){
		super(name);
	}

}
