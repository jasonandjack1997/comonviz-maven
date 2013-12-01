package unused;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import database.model.data.DataModel;

@Entity
public class Activity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	String name;
//	public class ProcessActivity extends DataModel {

	@OneToMany(mappedBy="activity")
	private Set<Objective> objectives = new HashSet<Objective>(
			0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Objective> getObjectives() {
		return objectives;
	}

	public void setObjectives(Set<Objective> processObjective) {
		this.objectives = processObjective;
	}


	// public static void main(String args[]) throws IntrospectionException,
	// IllegalAccessException, IllegalArgumentException,
	// InvocationTargetException {
	// ProcessActivity pa = new ProcessActivity();
	//
	// for (Field f : pa.getClass().getDeclaredFields()) {
	// f.setAccessible(true);
	// Object o = null;
	// try {
	// o = f.get(pa);
	// ;
	// if (DataModel.class.isAssignableFrom(f.getDeclaringClass())) {
	// int a = 1;
	// }
	// } catch (Exception e) {
	// o = e;
	// }
	// System.out.println(f.getGenericType() + " " + f.getName() + " = "
	// + o);
	// }
	//
	// return;
	// }
}
