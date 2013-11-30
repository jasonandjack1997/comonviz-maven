package database.model.data.bussinesProcessManagement;

import java.beans.IntrospectionException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import database.model.data.DataModel;

//@Entity
public class ProcessActivity extends DataModel {

	private Set<StockDailyRecord> stockDailyRecords = new HashSet<StockDailyRecord>(
			0);

	public ProcessActivity(Set<StockDailyRecord> stockDailyRecords) {
		this.stockDailyRecords = stockDailyRecords;
	}

	public int compareTo(ProcessActivity o) {
		return super.compareTo(o);
	}

	@OneToMany(fetch = FetchType.LAZY)
	public Set<StockDailyRecord> getStockDailyRecords() {
		return this.stockDailyRecords;
	}

	public void setStockDailyRecords(Set<StockDailyRecord> stockDailyRecords) {
		this.stockDailyRecords = stockDailyRecords;
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
