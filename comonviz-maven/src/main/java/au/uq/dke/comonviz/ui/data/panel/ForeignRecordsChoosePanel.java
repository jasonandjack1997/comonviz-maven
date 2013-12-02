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

import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.BasicTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.PrimeryRecordsTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.ServiceTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

public class ForeignRecordsChoosePanel extends ButtonedTablePanel {

	private BasicRecord mainRecord;
	private BasicTablePanel callerTablePanel;

	public ForeignRecordsChoosePanel(BasicRecord primaryRecord, Class<?> foreignClass, BasicTablePanel
			 callerTablePanel) {
		super();
		PrimeryRecordsTableModel foreignTableModel = new PrimeryRecordsTableModel(
				foreignClass);
		JTable table = new BasicTable(foreignTableModel);
		super.init(table);
		this.callerTablePanel = callerTablePanel;
		this.mainRecord = primaryRecord;
	}

	/**
	 * add the selected record(s) as the associated records to the pk record
	 */
	@SuppressWarnings("unchecked")
	@UiAction
	public void OK() {
		int[] selectedRows = this.getTable().getSelectedRows();
		for (int i = 0; i < selectedRows.length; i++) {
			BasicRecord record = ((BasicTableModel) this.getTable().getModel())
					.getValueAt(selectedRows[i]);

			// main record should add selected record to it's set
			Set<BasicRecord> associatedSet = ReflectionUtils
					.getSpecificSetByElementType(mainRecord,
							(Class<BasicRecord>) record.getClass());
			boolean added = associatedSet.add(record);

			if (added) {
				// the selected record should set the main record as the
				// association
				String property = ReflectionUtils.getFieldNameByType(
						(Class<BasicRecord>) record.getClass(),
						this.mainRecord.getClass());
				ClassUtils.setProperty(record, property, mainRecord);

				// update callerTableModel
				this.callerTablePanel.getTableModel().add(record);
			}
		}

		Container parent = this.getRootPane();
		while (!(parent instanceof JDialog)) {
			parent = parent.getParent();
		}
		((JDialog) parent).dispose();

		return;

	}

}
