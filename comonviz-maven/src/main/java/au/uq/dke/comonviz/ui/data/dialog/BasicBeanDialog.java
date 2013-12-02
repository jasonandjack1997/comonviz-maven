package au.uq.dke.comonviz.ui.data.dialog;

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
import org.springframework.ui.Model;

import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import au.uq.dke.comonviz.ui.data.tableModel.ServiceTableModel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

public class BasicBeanDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private BasicTablePanel tablePanel;
	private BasicRecord dataModel;
	private SwingMetawidget modelWidget;
	private SwingMetawidget buttonsWidget;
	
	/**
	 * use by save, if isUpdate, call model#updateRecord(), else call @model#save()
	 */
	private boolean isUpdate;

	public BasicBeanDialog(BasicTablePanel basicTablePanel, BasicRecord dataModel, boolean isUpdate) {

		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle(this.getClass().getSimpleName());

		this.tablePanel = basicTablePanel;
		this.dataModel = dataModel;
		this.isUpdate = isUpdate;

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
		BasicRecord model = modelWidget.getToInspect();
		this.dataModel.update(model);

		if(this.isUpdate){
			// update old record
			((ServiceTableModel)this.tablePanel.getTable().getModel()).updateRecord(dataModel);
		} else{
			// add new record
			((ServiceTableModel)this.tablePanel.getTable().getModel()).add(dataModel);
		}
		
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
	
	public SwingMetawidget getModelWidget() {
		return modelWidget;
	}


}