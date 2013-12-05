package database.model.data.businessProcessManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import database.model.data.BasicRecord;
import database.model.data.BasicRecordSet;


@Entity
public class ProcessRule extends BusinessProcess {
	public ProcessRule(String name){
		super(name);
	}

	@OneToMany (mappedBy = "processRule")
	private Set<ProcessActivity> processActivities =  new BasicRecordSet<ProcessActivity>();


	public Set<ProcessActivity> getProcessActivities() {
		return processActivities;
	}

	public void setProcessActivities(Set<ProcessActivity> objectives) {
		this.processActivities = objectives;
	}
	

}
