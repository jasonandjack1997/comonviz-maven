package database.model.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.inspector.annotation.UiLarge;

import database.model.Trackable;

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
	
}
