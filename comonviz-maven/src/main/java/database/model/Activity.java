package database.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Activity {
	public Activity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String name;
	
	@OneToMany (mappedBy = "activity")
	Set<Objective> objectives;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Objective> getObjectives() {
		return objectives;
	}

	public void setObjectives(Set<Objective> objectives) {
		this.objectives = objectives;
	}
	

}
