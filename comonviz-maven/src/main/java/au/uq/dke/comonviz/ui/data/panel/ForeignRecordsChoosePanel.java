package au.uq.dke.comonviz.ui.data.panel;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.util.ClassUtils;

import au.uq.dke.comonviz.ui.data.tableModel.BasicTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.ServiceTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtil;
import database.model.data.BasicRecord;

public class ForeignRecordsChoosePanel extends ButtonedTablePanel {

	private BasicRecord mainRecord;
	private BasicTableModel callerTableModel;

	public ForeignRecordsChoosePanel(JTable table, BasicRecord mainRecord,
			BasicTableModel callerTableModel) {
		super(table);
		this.callerTableModel = callerTableModel;
		this.mainRecord = mainRecord;
	}

	/**
	 * add the selected record(s) as the associated records to the pk record
	 */
	@SuppressWarnings("unchecked")
	@UiAction
	public void OK() {
		int[] selectedRows = this.getTable().getSelectedRows();
		for (int i = 0; i < selectedRows.length; i++) {
			BasicRecord record = ((BasicTableModel) this.getTable()
					.getModel()).getValueAt(selectedRows[i]);

			// main record should add selected record to it's set
			Set<BasicRecord> associatedSet = ReflectionUtil.getSpecificSet(
					mainRecord, (Class<BasicRecord>) record.getClass());
			associatedSet.add(record);

			// the selected record should set the main record as the association
			String property = ReflectionUtil.getFieldName(
					(Class<BasicRecord>) record.getClass(),
					this.mainRecord.getClass());
			ClassUtils.setProperty(record, property, mainRecord);

			// update callerTableModel
			this.callerTableModel.add(record);
		}

		Container parent = this.getRootPane();
		while (!(parent instanceof JDialog)) {
			parent = parent.getParent();
		}
		((JDialog) parent).dispose();

		return;

	}

}
