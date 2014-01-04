package database.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ReviewTool extends Tool	{
	public ReviewTool(String name){
		super(name);
	}
	
	public ReviewTool() {

	}
	

}
