package au.uq.dke.comonviz.ui.data.tableModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;

import org.hibernate.Session;
import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

import au.uq.dke.comonviz.utils.DatabaseUtils;
import database.model.data.BasicRecord;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class DatabaseTableModel<T extends BasicRecord> extends
		BasicTableModel<T> {

	private static final long serialVersionUID = 1L;
	
	private Session session = DatabaseUtils.getSession();



	/**
	 * only set the clazz and service
	 * <P>
	 * data population is done in {@link #init(String...)}
	 * 
	 * @param clazz
	 */
	public DatabaseTableModel(Class<T> clazz) {
		super(clazz);
	}

	public void init(String... columns) {
		super.initColumns(columns);
		Collection<T> collection = DatabaseUtils.findAll(this.getmClass());
//		Collection<T> completedRecords = new ArrayList<T>();
//		for(T record : collection){
//			record = (T) service.find(record);
//			completedRecords.add(record);
//		}
		super.importCollection(collection);
	}

	//
	// Public methods
	//

	public void add(T record) {
		super.add(record);
		persist(record);
	}

	public void updateRecord(T newRecord) {
		super.updateRecord(newRecord);
		persist(newRecord);

	}

	public void delete(T record) {
		super.delete(record);
		persist(record);
	}
	
	private void persist(T record){
		session.beginTransaction();
		session.save(record);
		session.getTransaction().commit();

	}


}
