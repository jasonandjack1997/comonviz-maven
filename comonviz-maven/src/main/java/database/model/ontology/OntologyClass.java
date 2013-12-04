package database.model.ontology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.metawidget.inspector.annotation.UiHidden;

import database.model.Trackable;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class OntologyClass extends Trackable{
	private Long branchId;
	@Column(columnDefinition = "TEXT")
	private String discription;
	private String iri;
	private int level;
	private int siblingRank;

	public Long getBranchId() {
		return branchId;
	}


	public String getDiscription() {
		return discription;
	}

	public String getIri() {
		return iri;
	}

	public int getLevel() {
		return level;
	}


	public int getSiblingRank() {
		return siblingRank;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public void setIri(String iri) {
		this.iri = iri;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setSiblingRank(int order) {
		this.siblingRank = order;
	}
	
	@UiHidden
	public String getNameAndIRI(){
		return super.toString() + ", iri: " + iri;
	}
	
	public void update(OntologyClass c){
		this.setBranchId(c.getBranchId());
		this.setDiscription(c.getDiscription());
		this.setIri(c.getIri());
		this.setSiblingRank(c.getSiblingRank());
		super.update(c);
	}


}
