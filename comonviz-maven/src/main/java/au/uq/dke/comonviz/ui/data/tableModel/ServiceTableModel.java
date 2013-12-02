package au.uq.dke.comonviz.ui.data.tableModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;

import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

import database.model.data.BasicRecord;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class ServiceTableModel<T extends BasicRecord> extends
		BasicTableModel<T> {

	private static final long serialVersionUID = 1L;

	private GenericService service;


	/**
	 * only set the clazz and service
	 * <P>
	 * data population is done in {@link #init(String...)}
	 * 
	 * @param clazz
	 */
	public ServiceTableModel(Class<T> clazz) {
		super(clazz);
		service = ServiceManager.getGenericService(clazz);
	}

	public void init(String... columns) {
		super.initColumns(columns);
		Collection collection = service.findAll();
		super.importCollection(collection);
	}

	//
	// Public methods
	//

	public void add(T record) {
		super.add(record);
		service.save(record);
	}

	public void updateRecord(T newRecord) {
		super.updateRecord(newRecord);
		service.save(newRecord);

	}

	public void delete(T record) {
		super.delete(record);
		service.delete(record);
	}

	public GenericService getService() {
		return service;
	}

}
