package database.model.data;

import javax.persistence.Entity;

@Entity
public class BigCity extends City{
	
	private int population = 0;

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

}
