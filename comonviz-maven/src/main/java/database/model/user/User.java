package database.model.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import org.metawidget.inspector.annotation.UiHidden;

import database.model.data.BasicRecord;
import database.model.data.BasicRecordSet;

public class User extends BasicRecord{

	@UiHidden
	@ManyToMany (cascade = CascadeType.PERSIST)
	private Set<Role> associatedRoles = new BasicRecordSet<Role>();
	
	
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getAssociatedRoles() {
		return associatedRoles;
	}

	public void setAssociatedRoles(Set<Role> associatedRoles) {
		this.associatedRoles = associatedRoles;
	}
	
	public User(){
		
	}
	
	public User(String name){
		super(name);
	}

	@UiHidden
	@Override
	public String getDiscription() {
		// TODO Auto-generated method stub
		return super.getDiscription();
	}
	
	
}

