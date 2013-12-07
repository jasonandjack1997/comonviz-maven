package au.uq.dke.comonviz.ui.data.panel;

import java.awt.BorderLayout;

import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiHidden;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.misc.CustomRuntimeException;

public class ButtonedTablePanel extends BasicTablePanel {

	private SwingMetawidget buttonsWidget;

	/**the caller can call either {@link #ButtonedTablePanel(JTable)}, 
	 * <p>
	 * or call {@link #ButtonedTablePanel()} then call init
	 * @param table
	 */
	public ButtonedTablePanel(JTable table) {
		init(table);
	}

	public ButtonedTablePanel() {
		// empty
	}

	public void init(JTable table) {
		if (super.getTable() == null) {
			super.init(table);
			buttonsWidget = new SwingMetawidget();
			buttonsWidget.addWidgetProcessor(new BeansBindingProcessor(
					new BeansBindingProcessorConfig()));
			buttonsWidget.addWidgetProcessor(new ReflectionBindingProcessor());
			buttonsWidget.setToInspect(this);
			buttonsWidget.setMaximumInspectionDepth(1);

			this.add(buttonsWidget, BorderLayout.SOUTH);
		}else {
			throw new CustomRuntimeException("table has already been initialized!");
		}
	}

}
