package au.uq.dke.comonviz.ui.data.dialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import au.uq.dke.comonviz.ui.data.panel.AssociatedRecordsPanel;
import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

public class PrimeryRecordBeanDialog extends BasicBeanDialog {
	private static final long serialVersionUID = 1L;

	/**
	 * @param basicTablePanel
	 * @param primaryRecord
	 * @param isUpdate
	 */
	public PrimeryRecordBeanDialog(BasicRecord primaryRecord, boolean isUpdate,
			BasicTablePanel basicTablePanel) {
		super();

		// add associated record
		List<Set<?>> setList = ReflectionUtils.getSetObjectList(primaryRecord);
		List<Object> fkRecordList = new ArrayList<Object>();// they are fk
															// records

		List<Field> setFields = ReflectionUtils.getSetFieldList(primaryRecord
				.getClass());
		for (Field setField : setFields) {
			Class setElementType = ReflectionUtils.getSetElementType(setField);
			Set set = ReflectionUtils.getSpecificSetByElementType(
					primaryRecord, setElementType);
			JPanel associatedRecordsPanel = new AssociatedRecordsPanel(
					primaryRecord, set, setElementType);

			JLabel typeLabel = new JLabel("Associated "
					+ setElementType.getClass().getSimpleName() + ": ");
			this.getModelWidget().add(typeLabel);
			this.getModelWidget().add(associatedRecordsPanel);
		}

		super.init(basicTablePanel, primaryRecord, isUpdate);
	}

}