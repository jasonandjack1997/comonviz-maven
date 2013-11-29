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

public class GenericRecordDialogDebug extends JDialog {
	private static final long serialVersionUID = 1L;

	public GenericRecordDialogDebug(GenericDashboardDialog dashboard,
			DataModel dataModel) {
		
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.pack();
		this.setVisible(true);

	}

	@UiAction
	public void save() {

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

	public static void main(String args[]) {

		new GenericRecordDialogDebug(null, null);
		return;
	}
}