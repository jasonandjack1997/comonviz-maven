package au.uq.dke.comonviz.ui.dataUI;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

import javax.swing.JFrame;
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

import au.uq.dke.comonviz.ui.refactor.ListTableModel;
import database.model.data.DataModel;
import database.model.data.bussinesProcessManagement.ProcessRule;
import database.service.GenericService;
import database.service.ServiceManager;

public class GenericDashboardDialog<R extends DataModel> extends JFrame {

	private static final long serialVersionUID = 1L;
	private Class<R> clazz;
	private SwingMetawidget buttonsWidget;

	private ListTableModel<?> mainListTableModel;
	private JTable mainListTable;
	private JScrollPane mainScrollPane;

	private GenericService mainListService;

	@UiHidden
	public GenericService getMainListService() {
		return mainListService;
	}

	public GenericDashboardDialog(Class<R> clazz) {

		this.clazz = clazz;
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		try {
			mainListService = ServiceManager.getService(clazz);
		} catch (BeansException e) {
			e.printStackTrace();
		}

		//this.setModalityType(ModalityType.APPLICATION_MODAL);

		buttonsWidget = new SwingMetawidget();
		buttonsWidget.addWidgetProcessor(new BeansBindingProcessor(
				new BeansBindingProcessorConfig()));
		buttonsWidget.addWidgetProcessor(new ReflectionBindingProcessor());
		buttonsWidget.setToInspect(this);


		mainListTableModel = new ListTableModel<R>(clazz, mainListService.findAll() , "name");

		mainListTableModel.setEditable(true);
		mainListTable = new JTable(mainListTableModel);
		mainListTable.setAutoCreateColumnsFromModel(true);

		mainListTable.setDefaultRenderer(Set.class,
				new DefaultTableCellRenderer() {

					/**
					 * 
					 */
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
		this.add(buttonsWidget, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
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
		new GenericRecordDialogDebug(this, model).setVisible(true);
	}

	@UiAction
	public void edit() {

		@SuppressWarnings("unchecked")
		R record = (R) this.mainListTableModel.getValueAt(mainListTable
				.getSelectedRow());
		new GenericRecordDialog(this, record).setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@UiAction
	public void delete() {
		R record = (R) this.mainListTableModel.getValueAt(mainListTable
				.getSelectedRow());

		//update database
		this.mainListService.delete(record);
		// update list
		this.updateDashboard();

	}

	public void updateDashboard() {
		//update the main list
		this.mainListTableModel.importCollection(this.mainListService.findAll());

	}

	public static void main(String args[]) throws BeansException, InstantiationException, IllegalAccessException{
		
		Class<ProcessRule> clazz = ProcessRule.class;
		
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		GenericService service = ServiceManager.getService(clazz);
		
		service.deleteAll();
		ProcessRule pr1 = new ProcessRule();
		pr1.setName("process rule 2");
		service.save(pr1);

		new GenericDashboardDialog<ProcessRule>(ProcessRule.class);
		ctx.close();
		return;
	}
}
