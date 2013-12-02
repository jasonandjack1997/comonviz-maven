package au.uq.dke.comonviz.ui.data.recordDialog;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import au.uq.dke.comonviz.utils.ReflectionUtil;
import database.model.data.BasicRecord;

public class PKRecordBeanDialog extends BasicBeanDialog {
	private static final long serialVersionUID = 1L;

	public PKRecordBeanDialog(BasicTablePanel basicTablePanel,
			BasicRecord dataModel, boolean isUpdate) {
		super(basicTablePanel, dataModel, isUpdate);

		// add associated record
		List<Set<?>> setList = ReflectionUtil.getSetObjectList(dataModel);
		List<Object> fkRecordList = new ArrayList<Object>();// they are fk
															// records

		if (setList != null) {
			for (Set<?> set : setList) {
				if (set != null && set.size() > 0) {
					fkRecordList.add(set.toArray()[0]);
				} else {

				}
			}
		}

		for (Object record : fkRecordList) {
			JLabel typeLabel = new JLabel("Associated "
					+ record.getClass().getSimpleName() + ": ");
			JLabel nameLabel = new JLabel(((BasicRecord) record).getName());
			this.getModelWidget().add(typeLabel);
			this.getModelWidget().add(nameLabel);
		}

	}

}