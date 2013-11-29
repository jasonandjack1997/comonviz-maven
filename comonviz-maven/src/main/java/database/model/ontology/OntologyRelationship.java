package database.model.ontology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import au.uq.dke.comonviz.EntryPoint;
import database.model.Trackable;


@Entity
@PrimaryKeyJoinColumn(name="ID")
public class OntologyRelationship extends Trackable implements Comparable<OntologyRelationship> {
	
	@Column(columnDefinition = "TEXT")
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	private Long srcClassId;
	private Long dstClassId;
	private Long axiomId;
	private boolean isBidirection;
	public Long getSrcClassId() {
		return srcClassId;
	}
	public Long getDstClassId() {
		return dstClassId;
	}
	public Long getAxiomId() {
		return axiomId;
	}
	public boolean isBidirection() {
		return isBidirection;
	}
	public void setSrcClassId(Long srcClassId) {
		this.srcClassId = srcClassId;
	}
	public void setDstClassId(Long dstClassId) {
		this.dstClassId = dstClassId;
	}
	public void setAxiomId(Long axiomId) {
		this.axiomId = axiomId;
	}
	public void setBidirection(boolean isBidirection) {
		this.isBidirection = isBidirection;
	}
	
	public String getSourceName(){
		if(this.srcClassId == null){
			return "";
		}
		
		if(EntryPoint.getOntologyClassService().findById(this.srcClassId) == null){
			return "";
		}
		return EntryPoint.getOntologyClassService().findById(this.srcClassId).getName();
	}
	
	public String getDestinationName(){
		if(this.dstClassId == null){
			return "";
		}
		if(EntryPoint.getOntologyClassService().findById(this.dstClassId) == null){
			return "";
		}

		return EntryPoint.getOntologyClassService().findById(this.dstClassId).getName();
	}
	
	public int compareTo(OntologyRelationship o) {
		// TODO Auto-generated method stub
		return super.compareTo(o);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.getId() == ((OntologyRelationship)obj).getId();
	}
	
}
