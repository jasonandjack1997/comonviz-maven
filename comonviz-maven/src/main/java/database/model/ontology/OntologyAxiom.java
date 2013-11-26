package database.model.ontology;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import database.model.Trackable;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class OntologyAxiom extends Trackable implements Comparable<OntologyAxiom>{
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
	public int compareTo(OntologyAxiom o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
