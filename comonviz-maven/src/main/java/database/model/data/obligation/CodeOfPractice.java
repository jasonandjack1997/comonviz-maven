package database.model.data.obligation;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CodeOfPractice extends VoluntaryObligation	{
	public CodeOfPractice(String name){
		super(name);
	}

}
