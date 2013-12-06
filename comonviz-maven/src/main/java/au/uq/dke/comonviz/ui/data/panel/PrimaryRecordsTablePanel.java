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
import au.uq.dke.comonviz.ui.data.tableModel.DatabaseTableModel;
import au.uq.dke.comonviz.utils.DatabaseUtils;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;
import database.model.data.businessProcessManagement.ProcessObjective;
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
			newRecord = (BasicRecord) ((DatabaseTableModel<?>)this.getTable().getModel()).getmClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new PrimaryRecordBeanDialog(newRecord, false, this);

	}

	@UiAction
	public void edit() {
		int selectedRow = this.getTable()
				.getSelectedRow();
		if(selectedRow < 0){
			return;
		}

		BasicRecord record = (BasicRecord) ((DatabaseTableModel<?>)this.getTable().getModel()).getValueAt(selectedRow);
		//record = ((ServiceTableModel)this.getTableModel()).getService().findByName(record.getName(), record.getClass());
		new PrimaryRecordBeanDialog(record, true, this);
	}

	@SuppressWarnings("unchecked")
	@UiAction
	public void delete() {
		BasicRecord record = (BasicRecord) ((DatabaseTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		//update relation

		
		//reset all the record sets
		for(Field setField: ReflectionUtils.getSetFieldList(record.getClass())){
			Class<?> elementType = ReflectionUtils.getSetElementType(setField);
			Set<BasicRecord> set = ReflectionUtils.getSpecificSetByElementType(record, elementType);
			set.clear();
		}
		

		//update database
		DatabaseUtils.getSession().delete(record);
		

		//update table
		((DatabaseTableModel<BasicRecord>)this.getTable().getModel()).delete(record);
		return;

	}
	
}
