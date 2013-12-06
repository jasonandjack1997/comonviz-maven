package database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Stakeholder extends Structure	{
	
	@Enumerated(EnumType.ORDINAL)
	private Type stakeholderType;
	
	private String obligation;
	
	public Stakeholder(String name){
		super(name);
	}
	
	private enum Type{
		TypeA,
		TypeB
	}

}
