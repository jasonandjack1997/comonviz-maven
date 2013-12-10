package au.uq.dke.comonviz.ui.data.dialog;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.metawidget.util.ClassUtils;

import au.uq.dke.comonviz.ui.data.panel.AssociatedRecordsPanel;
import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import au.uq.dke.comonviz.utils.StringUtils;
import database.model.data.BasicRecord;

public class PrimaryRecordBeanDialog extends BasicBeanDialog {
	private static final long serialVersionUID = 1L;

	/**
	 * @param callerTablePanel
	 * @param primaryRecord
	 * @param isUpdate
	 */
	public PrimaryRecordBeanDialog(BasicRecord primaryRecord, boolean isUpdate,
			BasicTablePanel callerTablePanel) {
		super();

		this.setTitle(StringUtils.getUserFriendlyName(primaryRecord.getClass().getSimpleName()));
		// add associated record
		List<Set<?>> setList = ReflectionUtils.getSetObjectList(primaryRecord);
		List<Object> fkRecordList = new ArrayList<Object>();// they are fk
															// records

		List<Field> nonSetFields = ReflectionUtils
				.getNonSetFieldList(primaryRecord.getClass());
		for (Field nonSetField : nonSetFields) {

			JLabel typeLabel = new JLabel(
					StringUtils.getUserFriendlyName(nonSetField.getName())
							+ ": ");
			this.getModelWidget().add(typeLabel);
			Object property = ClassUtils.getProperty(primaryRecord, nonSetField.getName());
			String value = "N/A";
			if(property != null){
				value = property.toString();
			}
			JLabel valueLable = new JLabel(value);
			this.getModelWidget().add(valueLable);
		}

		List<Field> setFields = ReflectionUtils.getSetFieldList(primaryRecord
				.getClass());
		for (Field setField : setFields) {
			Class setElementType = ReflectionUtils.getSetElementType(setField);
			Set set = ReflectionUtils.getSpecificSetByElementType(
					primaryRecord, setElementType);
			JPanel associatedRecordsPanel = new AssociatedRecordsPanel(
					primaryRecord, set, setElementType);

			JLabel typeLabel = new JLabel("Associated "
					+ setElementType.getSimpleName() + ": ");
			this.getModelWidget().add(typeLabel);
			this.getModelWidget().add(associatedRecordsPanel);
		}

		super.init(callerTablePanel, primaryRecord, isUpdate);
	}

}