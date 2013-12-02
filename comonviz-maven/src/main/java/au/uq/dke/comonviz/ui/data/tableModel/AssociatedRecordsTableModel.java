package au.uq.dke.comonviz.ui.data.tableModel;

import java.util.Set;

import database.model.data.BasicRecord;
import database.service.GenericService;

public class AssociatedRecordsTableModel extends BasicTableModel{
	GenericService service;
	Set<Object> set;

	/**
	 * this panel is in the extends @BasicBeanDialog dialog as a field
	 * <p>
	 * it contains the records it associated with 
	 * 
	 * @param mainRecord
	 * @param set the associated records
	 */
	@SuppressWarnings("unchecked")
	public AssociatedRecordsTableModel(BasicRecord mainRecord, Set<?> set) {
		super(mainRecord.getClass());
		this.set = (Set<Object>) set;
		service = this.getService();
		super.init("name", "discription");
		super.importCollection(set);
	}

	@Override
	public void add(BasicRecord record) {
		set.add(record);
		//the other side 
		
		//update list
		super.importCollection(set);
	}

	@Override
	public void delete(BasicRecord record) {
		set.remove(record);
		//the other side
		
		//update list
		super.importCollection(set);
	}

}
