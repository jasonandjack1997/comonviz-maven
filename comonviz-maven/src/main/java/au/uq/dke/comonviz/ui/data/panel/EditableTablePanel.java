package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.ui.data.recordDialog.PKRecordBeanDialog;
import au.uq.dke.comonviz.ui.data.tableModel.BasicListTableModel;
import database.model.data.BasicRecord;

public class EditableTablePanel extends BasicTablePanel {

	private static final long serialVersionUID = 1L;
	private SwingMetawidget buttonsWidget;

	public EditableTablePanel(JTable table) {
		super(table);
		buttonsWidget = new SwingMetawidget();
		buttonsWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		buttonsWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		buttonsWidget.setToInspect(this);
	}

	@UiAction
	public void add() {
		BasicRecord newRecord = null;
		try {
			newRecord = (BasicRecord) ((BasicListTableModel<?>)this.getTable().getModel()).getmClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new PKRecordBeanDialog(this, newRecord, true);

	}

	@UiAction
	public void edit() {

		@SuppressWarnings("unchecked")
		BasicRecord record = (BasicRecord) ((BasicListTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		new PKRecordBeanDialog(this, record, true);
	}

	@SuppressWarnings("unchecked")
	@UiAction
	public void delete() {
		BasicRecord record = (BasicRecord) ((BasicListTableModel<?>)this.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());
		((BasicListTableModel<BasicRecord>)this.getTable().getModel()).delete(record);
		return;

	}

}
