package database.model.data.program;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ControlSelection extends ControlAndMonitoring	{
	
	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private ControlIdentificationAndDefination control;
	
	@Enumerated(EnumType.ORDINAL) 
	private ControlRank controlRank;
	
	
	
	public ControlSelection(String name){
		super(name);
	}
	
	private enum ControlRank{
		High, 
		Medium,
		Low
	}

}
