package au.uq.dke.comonviz.ui.dataUI;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.metawidget.inspector.annotation.UiAction;
import org.metawidget.inspector.annotation.UiHidden;
import org.metawidget.swing.SwingMetawidget;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessor;
import org.metawidget.swing.widgetprocessor.binding.beansbinding.BeansBindingProcessorConfig;
import org.metawidget.swing.widgetprocessor.binding.reflection.ReflectionBindingProcessor;
import org.metawidget.util.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import au.uq.dke.comonviz.ui.refactor.MainListTableMode;
import au.uq.dke.comonviz.utils.ReflectionUtil;
import database.model.data.DataModel;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.model.data.bussinesProcessManagement.ProcessRule;
import database.service.GenericService;
import database.service.ServiceManager;

public class BasicTableUnit<R extends DataModel> extends JPanel {

	private static final long serialVersionUID = 1L;
	private Class<R> clazz;
	private SwingMetawidget buttonsWidget;

	private MainListTableMode<?> listTableModel;
	private JTable mainListTable;
	private JScrollPane mainScrollPane;
	private GenericService listService;
	private JPanel mainListPane;

	@UiHidden
	public GenericService getListService() {
		return listService;
	}

	public BasicTableUnit(Class<R> clazz) {

		this.clazz = clazz;
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		try {
			listService = ServiceManager.getGenericService(clazz);
		} catch (BeansException e) {
			e.printStackTrace();
		}

		buttonsWidget = new SwingMetawidget();
		buttonsWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		buttonsWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		buttonsWidget.setToInspect(this);

		
		// before cast to array we should fetch the set members!

		List<R> results = listService.findAll();
		
		List<R> resultsWithSetsObjects = new ArrayList<R>();
		for(R r : results){
			DataModel record = r;
			r = (R) listService.findByName(record.getName(), clazz);
			resultsWithSetsObjects.add(r);
		}
				
//				for (T t : collection) {
//			ReflectionUtil.getSetObjectList(mainObject);
//		}

		
		listTableModel = new MainListTableMode<R>(clazz,resultsWithSetsObjects
				);

		listTableModel.setEditable(true);
		mainListTable = new JTable(listTableModel);
		mainListTable.setAutoCreateColumnsFromModel(true);

		mainListTable.setDefaultRenderer(Set.class,
				new DefaultTableCellRenderer() {
					private static final long serialVersionUID = 1L;

					@Override
					public void setValue(Object value) {

						setText(CollectionUtils.toString((Set<?>) value));
					}
				});

		mainListTable.setRowHeight(25);
		mainListTable.setShowVerticalLines(true);
		mainListTable.setCellSelectionEnabled(false);
		mainListTable.setRowSelectionAllowed(true);

		mainListTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {

				if (event.getClickCount() != 2) {
					return;
				}
			}
		});

		mainScrollPane = new JScrollPane(mainListTable);
		this.add(mainScrollPane, BorderLayout.CENTER);
		this.add(buttonsWidget, BorderLayout.EAST);
		// this.setVisible(true);
	}

	@UiAction
	public void add() {
		R model = null;
		try {
			model = this.clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDialog dlg = new GenericRecordDialog(this, model);

		// JDialog dlg = new JDialog();
		// dlg.setModalityType(ModalityType.APPLICATION_MODAL);
		// dlg.setVisible(true);
	}

	@UiAction
	public void edit() {

		@SuppressWarnings("unchecked")
		R record = (R) this.listTableModel.getValueAt(mainListTable
				.getSelectedRow());
		new GenericRecordDialog(this, record).setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@UiAction
	public void delete() {
		R record = (R) this.listTableModel.getValueAt(mainListTable
				.getSelectedRow());

		// update database
		this.listService.delete(record);
		// update list
		this.updateTable();
		List a = ServiceManager.getGenericService(ProcessObjective.class).findAll();
		return;

	}

	public void updateTable() {
		// update the main list
		this.listTableModel
				.importCollection(this.listService.findAll());

	}

	public static void main(String args[]) throws BeansException,
			InstantiationException, IllegalAccessException {

		Class<ProcessRule> clazz = ProcessRule.class;

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		GenericService service = ServiceManager.getGenericService(clazz);

		service.deleteAll();
		ProcessRule pr1 = new ProcessRule();
		pr1.setName("process rule 2");
		service.save(pr1);

		JDialog dlg = new JDialog();
		dlg.add(new BasicTableUnit<ProcessRule>(ProcessRule.class));
		dlg.pack();
		dlg.setVisible(true);
		ctx.close();
		return;
	}
}
