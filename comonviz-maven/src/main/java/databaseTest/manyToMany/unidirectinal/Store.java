package databaseTest.manyToMany.unidirectinal;

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
 
   public Set<City> getImplantedIn() {
    	return implantedIn;
    }
}