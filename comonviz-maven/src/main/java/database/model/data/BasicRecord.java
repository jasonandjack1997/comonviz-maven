package database.model.data;

import java.lang.reflect.Field;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.inspector.annotation.UiLarge;

import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.Trackable;
import database.service.GenericService;
import database.service.ServiceManager;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class BasicRecord extends Trackable implements Comparable<BasicRecord>{

	@Column(columnDefinition = "TEXT")
	private String discription = "";
	
	public BasicRecord(){
		
	}
	public BasicRecord(String name){
		super(name);
	}

	@UiComesAfter ("name")
	@UiLarge
	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	@Override
	public int compareTo(BasicRecord o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public GenericService getService(){
		return ServiceManager.getGenericService(this.getClass());
	}
	
	public BasicRecord getFullObject(){
		return this.getService().findByName(this.getName(), this.getClass());
	}
	public void persist(){
		ServiceManager.getGenericService(this.getClass()).save(this);
	}
	
	/**
	 * persist the records in the sets
	 */
	public void persistForeignRecordsInSetList(){
		//persist itself first
		this.persist();
		//persist the references
		for(Field setField : ReflectionUtils.getSetFieldList(this.getClass())){
			Class<?> elementType = ReflectionUtils.getSetElementType(setField);
			Set<BasicRecord> set = ReflectionUtils.getSpecificSetByElementType(this, elementType);
			for(BasicRecord foreignRecord : set){
				foreignRecord.persist();
			}
		}
		
	}
	
	public String getfieldNameByClass(){
		return ReflectionUtils.getfieldNameByClass(this.getClass());
	}

}
