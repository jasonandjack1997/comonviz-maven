package au.uq.dke.comonviz.ui.data.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import au.uq.dke.comonviz.ui.data.panel.AssociatedRecordsPanel;
import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.AssociatedRecordsTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

public class PrimeryRecordBeanDialog extends BasicBeanDialog {
	private static final long serialVersionUID = 1L;

	/**
	 * @param basicTablePanel
	 * @param primaryRecord
	 * @param isUpdate
	 */
	public PrimeryRecordBeanDialog(BasicTablePanel basicTablePanel,
			BasicRecord primaryRecord, boolean isUpdate) {
		super(basicTablePanel, primaryRecord, isUpdate);

		// add associated record
		List<Set<?>> setList = ReflectionUtils.getSetObjectList(primaryRecord);
		List<Object> fkRecordList = new ArrayList<Object>();// they are fk
															// records
		
		JPanel associationPanel = null;

		if (setList != null) {
			for (Set<?> set : setList) {
				TableModel associatedRecordsTableModel = new AssociatedRecordsTableModel(primaryRecord, set, null);
				JTable associatedRecordsTable = new BasicTable(associatedRecordsTableModel);
				JPanel associatedRecordsPanel = new AssociatedRecordsPanel(associatedRecordsTable);

				JLabel typeLabel = new JLabel("Associated "
						+ String.class.getSimpleName() + ": ");
				this.getModelWidget().add(typeLabel);
				this.getModelWidget().add(associatedRecordsPanel);

			}
		}

	}

}