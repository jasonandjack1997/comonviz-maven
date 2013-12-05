package database.model.data.businessProcessManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import database.model.data.BasicRecord;
import database.model.data.BasicRecordSet;


/**
 * I cannot generalize the model since I should use hibernate annotation
 * one possible way is to use reflection to add annotation before loading spring
 * @author uqwwan10
 *
 * @param <T>
 */
@Deprecated 
public class GenericModel<T extends BasicRecord> extends BasicRecord {
	public GenericModel() {
	}
	
	public GenericModel(String name){
		super(name);
	}

	@OneToMany (mappedBy = "activity")
	private Set<ProcessObjective> objectives =  new BasicRecordSet<ProcessObjective>();


	public Set<ProcessObjective> getObjectives() {
		return objectives;
	}

	public void setObjectives(Set<ProcessObjective> objectives) {
		this.objectives = objectives;
	}
	
	

}
