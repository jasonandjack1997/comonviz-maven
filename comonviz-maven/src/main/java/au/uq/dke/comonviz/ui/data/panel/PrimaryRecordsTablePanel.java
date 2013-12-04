package au.uq.dke.comonviz.ui.data.panel;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;

import au.uq.dke.comonviz.ui.data.dialog.PrimaryRecordBeanDialog;
import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.ServiceTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.service.ServiceManager;

public class PrimaryRecordsTablePanel extends ButtonedTablePanel {

	private static final long serialVersionUID = 1L;

	public PrimaryRecordsTablePanel(Class<?> clazz) {
		super();
		RecordsTableModel foreignTableModel = new RecordsTableModel(
				clazz);
		JTable table = new BasicTable(foreignTableModel);
		

		
		super.init(table);
	}

	@UiAction
	public void add() {
		BasicRecord newRecord = null;
		try {
			newRecord = (BasicRecord) ((ServiceTableModel<?>)this.getTable().getModel()).getmClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new PrimaryRecordBeanDialog(newRecord, false, this);

	}

	@UiAction
	public void edit() {

		BasicRecord record = (BasicRecord) ((ServiceTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		record = ((ServiceTableModel)this.getTableModel()).getService().findByName(record.getName(), record.getClass());
		new PrimaryRecordBeanDialog(record, true, this);
	}

	@SuppressWarnings("unchecked")
	@UiAction
	public void delete() {
		BasicRecord record = (BasicRecord) ((ServiceTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		//update relation

		
		//reset all the record sets
		for(Field setField: ReflectionUtils.getSetFieldList(record.getClass())){
			Class<?> elementType = ReflectionUtils.getSetElementType(setField);
			Set<BasicRecord> set = ReflectionUtils.getSpecificSetByElementType(record, elementType);
			for(Iterator<BasicRecord> it = set.iterator(); it.hasNext(); ){
				BasicRecord foreignRecord = it.next();
				//f unassociate p
				ReflectionUtils.foreignRnassociatedPrimaryRecord(record, foreignRecord);
				//update in the database
				ServiceManager.getGenericService(foreignRecord.getClass()).save(foreignRecord);
			}
			set.clear();
		}
		
		List objectives = ServiceManager.getGenericService(ProcessObjective.class).findAll();

		//update database
		ServiceManager.getGenericService(record.getClass()).delete(record);
		
		objectives = ServiceManager.getGenericService(ProcessObjective.class).findAll();

		//update table
		((ServiceTableModel<BasicRecord>)this.getTable().getModel()).delete(record);
		return;

	}
	
}
