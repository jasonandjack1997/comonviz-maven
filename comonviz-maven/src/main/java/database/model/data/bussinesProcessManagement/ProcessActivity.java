package database.model.data.bussinesProcessManagement;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import database.model.data.DataModel;

@Entity
public class ProcessActivity extends DataModel {

	private Set<ProcessObjective> processObjectives = new HashSet<ProcessObjective>(
			0);

	@OneToMany(fetch = FetchType.LAZY)
	public Set<ProcessObjective> getProcessObjectives() {
		return processObjectives;
	}

	public void setProcessObjectives(Set<ProcessObjective> processObjective) {
		this.processObjectives = processObjective;
	}

	public int compareTo(ProcessActivity o) {
		return super.compareTo(o);
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
