package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.ui.data.recordDialog.PKRecordBeanDialog;
import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.BasicTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.PKRecordsTableModel;
import database.model.data.BasicRecord;
import database.model.data.bussinesProcessManagement.ProcessActivity;

public class PKRecordsTablePanel extends ButtonedTablePanel {

	private static final long serialVersionUID = 1L;

	public PKRecordsTablePanel(JTable table) {
		super(table);
		//getButtonsWidget().setToInspect(this);
	}

	@UiAction
	public void add() {
		BasicRecord newRecord = null;
		try {
			newRecord = (BasicRecord) ((BasicTableModel<?>)this.getTable().getModel()).getmClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new PKRecordBeanDialog(this, newRecord, true);

	}

	@UiAction
	public void edit() {

		BasicRecord record = (BasicRecord) ((BasicTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		new PKRecordBeanDialog(this, record, true);
	}

	@SuppressWarnings("unchecked")
	@UiAction
	public void delete() {
		BasicRecord record = (BasicRecord) ((BasicTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		((BasicTableModel<BasicRecord>)this.getTable().getModel()).delete(record);
		return;

	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		JTable table = new BasicTable(new PKRecordsTableModel(ProcessActivity.class));
		frame.add(new PKRecordsTablePanel(table));
		frame.pack();
		frame.setVisible(true);
		return;
	}

}
