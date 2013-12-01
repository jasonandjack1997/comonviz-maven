package au.uq.dke.comonviz.ui.dataUI;

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

import au.uq.dke.comonviz.utils.ReflectionUtil;
import database.model.data.DataModel;

public class GenericRecordDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private BasicTableUnit tableUnit;
	private DataModel dataModel;
	private SwingMetawidget modelWidget;
	private SwingMetawidget buttonsWidget;

	public GenericRecordDialog(BasicTableUnit genericTableUnit,
			DataModel dataModel) {

		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setTitle(this.getClass().getSimpleName());

		
		

		this.tableUnit = genericTableUnit;
		this.dataModel = dataModel;

		modelWidget = new SwingMetawidget();
		modelWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		modelWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		modelWidget.setToInspect(dataModel);

		//add associated record
		List<Set<?>> setList = ReflectionUtil.getSetObjectList(dataModel);
		List<Object> fkRecordList = new ArrayList<Object>();//they are fk records

		if (setList != null) {
			for (Set<?> set : setList) {
				if(set !=null && set.size() > 0){
					fkRecordList.add(set.toArray()[0]);
				}else{
					
					
				}
			}
		}

		
		for(Object record : fkRecordList){
			JLabel typeLabel = new JLabel("Associated " + record.getClass().getSimpleName() + ": ");
			JLabel nameLabel = new JLabel(((DataModel)record).getName());
			modelWidget.add(typeLabel);
			modelWidget.add(nameLabel);
		}
		
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
		this.tableUnit.getListService().save(model);

		// update list
		this.tableUnit.updateTable();

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

	// public static void main(String args[]) {
	//
	// new GenericRecordDialogDebug(null, null);
	// return;
	// }
}