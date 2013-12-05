package database.model.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

    @ManyToMany(cascade = CascadeType.PERSIST)
	private Set<City> implantedIn = new HashSet<City>();
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setImplantedIn(Set<City> implantedIn) {
		this.implantedIn = implantedIn;
	}

	private String name;
 
   public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

public Set<City> getImplantedIn() {
    	return implantedIn;
    }
}