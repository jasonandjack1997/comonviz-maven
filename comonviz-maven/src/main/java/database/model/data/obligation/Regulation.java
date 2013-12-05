package database.model.data.obligation;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Regulation extends MandatoryObligation	{
	public Regulation(String name){
		super(name);
	}

}
