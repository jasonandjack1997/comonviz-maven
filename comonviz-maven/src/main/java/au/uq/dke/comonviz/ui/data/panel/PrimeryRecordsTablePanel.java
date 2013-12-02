package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.ui.data.dialog.PrimeryRecordBeanDialog;
import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.ServiceTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.PrimeryRecordsTableModel;
import database.model.data.BasicRecord;
import database.model.data.bussinesProcessManagement.ProcessActivity;

public class PrimeryRecordsTablePanel extends ButtonedTablePanel {

	private static final long serialVersionUID = 1L;

	public PrimeryRecordsTablePanel(JTable table) {
		super(table);
		//getButtonsWidget().setToInspect(this);
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
		new PrimeryRecordBeanDialog(this, newRecord, true);

	}

	@UiAction
	public void edit() {

		BasicRecord record = (BasicRecord) ((ServiceTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		new PrimeryRecordBeanDialog(this, record, true);
	}

	@SuppressWarnings("unchecked")
	@UiAction
	public void delete() {
		BasicRecord record = (BasicRecord) ((ServiceTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		((ServiceTableModel<BasicRecord>)this.getTable().getModel()).delete(record);
		return;

	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		JTable table = new BasicTable(new PrimeryRecordsTableModel(ProcessActivity.class));
		frame.add(new PrimeryRecordsTablePanel(table));
		frame.pack();
		frame.setVisible(true);
		return;
	}

}
