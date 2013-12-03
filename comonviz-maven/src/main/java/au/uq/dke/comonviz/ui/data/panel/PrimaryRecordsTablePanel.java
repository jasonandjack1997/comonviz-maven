package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.ui.data.dialog.PrimaryRecordBeanDialog;
import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.ServiceTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import database.model.data.BasicRecord;
import database.model.data.bussinesProcessManagement.ProcessActivity;

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
		new PrimaryRecordBeanDialog(record, true, this);
	}

	@SuppressWarnings("unchecked")
	@UiAction
	public void delete() {
		BasicRecord record = (BasicRecord) ((ServiceTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		((ServiceTableModel<BasicRecord>)this.getTable().getModel()).delete(record);
		return;

	}
	
}
