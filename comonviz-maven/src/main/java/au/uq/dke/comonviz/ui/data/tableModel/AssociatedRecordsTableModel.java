package au.uq.dke.comonviz.ui.data.tableModel;

import java.util.Set;

import database.model.data.BasicRecord;
import database.service.GenericService;
import database.service.ServiceManager;

public class AssociatedRecordsTableModel extends BasicTableModel{
	Set<Object> set;
	private BasicRecord primaryRecord;

	/**
	 * this panel is in the extends @BasicBeanDialog dialog as a field
	 * <p>
	 * it contains the records it associated with 
	 * 
	 * @param primaryRecord
	 * @param set the associated records
	 */
	@SuppressWarnings("unchecked")
	public AssociatedRecordsTableModel(BasicRecord primaryRecord, Set<?> set, Class<?> clazz) {
		super(clazz);
		this.primaryRecord = primaryRecord;
		this.set = (Set<Object>) set;
		super.initColumns("name", "discription");
		super.importCollection(set);
	}

	public BasicRecord getPrimaryRecord() {
		return primaryRecord;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(BasicRecord record) {
		set.add(record);
		//the other side 
		
		//update list
		super.add(record);
	}

	@Override
	public void delete(BasicRecord record) {
		set.remove(record);
		//the other side
		
		//update list
		super.delete(record);
	}

}
