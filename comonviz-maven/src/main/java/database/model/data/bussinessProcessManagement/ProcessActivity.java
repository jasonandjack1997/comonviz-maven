package database.model.data.bussinessProcessManagement;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import database.model.OntologyDataModel;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class ProcessActivity extends OntologyDataModel implements Comparable<ProcessActivity>{
	

	@Override
	public int compareTo(ProcessActivity o) {
		return super.compareTo(o);
	}

	
	
}
