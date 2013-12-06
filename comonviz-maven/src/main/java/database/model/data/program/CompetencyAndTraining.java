package database.model.data.program;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecordSet;
import database.model.data.relatedEntity.Participant;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CompetencyAndTraining extends Program	{
	
	@ManyToMany( cascade = {CascadeType.PERSIST} )
	private Set<Participant> participants = new BasicRecordSet<Participant>();
	
	private String fuction;
	
	public CompetencyAndTraining(String name){
		super(name);
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public String getFuction() {
		return fuction;
	}

	public void setFuction(String fuction) {
		this.fuction = fuction;
	}

}
