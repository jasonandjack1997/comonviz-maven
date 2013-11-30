package au.uq.dke.comonviz.ui.dataUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.SwingUtilities;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiComesAfter;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;

import au.uq.dke.comonviz.EntryPoint;
import database.model.data.DataModel;
import database.model.ontology.OntologyAxiom;

public class GenericRecordDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private GenericDashboardDialog dashboard;
	private DataModel dataModel;
	private SwingMetawidget modelWidget;
	private SwingMetawidget buttonsWidget;

	public GenericRecordDialog(GenericDashboardDialog dashboard,
			DataModel dataModel) {
		
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle(this.getClass().getSimpleName());

		this.dashboard = dashboard;
		this.dataModel = dataModel;

		modelWidget = new SwingMetawidget();
		modelWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		modelWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		modelWidget.setToInspect(dataModel);

		buttonsWidget = new SwingMetawidget();
		buttonsWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		buttonsWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		buttonsWidget.setToInspect(this);

		this.add(modelWidget, BorderLayout.NORTH);
		this.add(buttonsWidget, BorderLayout.SOUTH);

		this.pack();
		this.setVisible(true);

	}

	@UiAction
	public void save() {
		// update bean
		modelWidget.getWidgetProcessor(BeansBindingProcessor.class).save(
				modelWidget);
		DataModel model = modelWidget.getToInspect();
		this.dataModel.update(model);

		// update database
		this.dashboard.getMainListService().save(model);

		// update list
		this.dashboard.updateDashboard();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				dispose();
			}
		});
		return;
	}

	@UiAction
	@UiComesAfter("save")
	public void cancel() {
		this.dispose();

	}

//	public static void main(String args[]) {
//
//		new GenericRecordDialogDebug(null, null);
//		return;
//	}
}