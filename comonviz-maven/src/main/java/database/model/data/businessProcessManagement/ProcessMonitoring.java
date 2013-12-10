package database.model.data.businessProcessManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import database.model.data.BasicRecord;
import database.model.data.BasicRecordSet;


@Entity
public class ProcessMonitoring extends BusinessProcessManagement {
	public ProcessMonitoring(){
		
	}
	
	public ProcessMonitoring(String name){
		super(name);
	}

    @ManyToMany(cascade = CascadeType.PERSIST)
	private Set<ProcessActivity> associatedProcessActivities =  new BasicRecordSet<ProcessActivity>();


	public Set<ProcessActivity> getAssociatedProcessActivities() {
		return associatedProcessActivities;
	}

	public void setAssociatedProcessActivities(Set<ProcessActivity> objectives) {
		this.associatedProcessActivities = objectives;
	}
	

}
