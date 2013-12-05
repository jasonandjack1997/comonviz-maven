package database.model.data.obligation;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Standard extends VoluntaryObligation	{
	public Standard(String name){
		super(name);
	}

}
