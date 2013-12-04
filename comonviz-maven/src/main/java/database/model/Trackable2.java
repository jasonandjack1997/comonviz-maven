package database.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import edu.umd.cs.piccolo.PNode;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Trackable2{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return "id: " + id + "," + "name: " + name ;
	}
	
	
	/** update itself using the new object
	 * @param o
	 */
	public void update(Trackable2 o){
		this.setName(o.name);
	}

	@Override
	public boolean equals(Object obj) {
		if(((Trackable2)obj).getId() == this.getId()){
			return true;
		}
		return false;
	}

	private Timestamp creationDateTime;
	private Timestamp modificationDateTime;

	private Long creationUserId;
	private Long modificationUserId;

	private Long databaseVersionId;

}
