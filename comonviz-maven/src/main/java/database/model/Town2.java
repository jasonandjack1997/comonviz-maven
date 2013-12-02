package database.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import database.model.data.BasicRecord;

@Entity
public class Town2 extends BasicRecord {
	int population;
	@OneToMany(mappedBy = "town")
	Set<Citizen2> citizens = new HashSet<Citizen2>();

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public Set<Citizen2> getCitizens() {
		return citizens;
	}

	public void setCitizens(Set<Citizen2> citizens) {
		this.citizens = citizens;
	}

	public Town2() {
		int a = 1;

	}

	public Town2(String name, Integer population) {
		this.setName(name);
		this.population = population;
	}
}
