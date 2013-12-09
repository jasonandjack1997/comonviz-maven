package database.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import database.model.data.BasicRecord;
import database.model.ontology.OntologyClass;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class UserRole extends BasicRecord{
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<OntologyClass> associatedOntologyClasses = new HashSet<OntologyClass>();

	public Set<OntologyClass> getAssociatedOntologyClasses() {
		return associatedOntologyClasses;
	}

	public void setAssociatedOntologyClasses(Set<OntologyClass> associatedClasses) {
		this.associatedOntologyClasses = associatedClasses;
	}
	
	public UserRole(){
	}

	public UserRole(String name){
		super(name);
	}
}
