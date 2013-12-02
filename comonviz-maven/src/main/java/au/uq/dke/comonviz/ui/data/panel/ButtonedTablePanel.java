package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JTable;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiHidden;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

public class ButtonedTablePanel extends BasicTablePanel{

	private SwingMetawidget buttonsWidget;
	public ButtonedTablePanel(JTable table) {
		super(table);
		buttonsWidget = new SwingMetawidget();
		buttonsWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		buttonsWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		buttonsWidget.setToInspect(this);
		buttonsWidget.setMaximumInspectionDepth(1);
		
		this.add(buttonsWidget);
	}

}
