package database.model.ontology;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import database.model.Trackable2;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class OntologyAxiom2 extends Trackable2 implements Comparable<OntologyAxiom2>{
	@Column(columnDefinition = "TEXT")
	private String discription = "hehe";

	@Column(columnDefinition = "TEXT")
	private String iri;

	public String getDiscription() {
		return discription;
	}

	public String getIri() {
		return iri;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public void setIri(String iri) {
		this.iri = iri;
	}

	public String toString(){
		return super.toString() + ", iri: " + iri;
	}

	@Override
	public int compareTo(OntologyAxiom2 o) {
		// TODO Auto-generated method stub
		return this.getName().compareToIgnoreCase(o.getName());
	}
}
