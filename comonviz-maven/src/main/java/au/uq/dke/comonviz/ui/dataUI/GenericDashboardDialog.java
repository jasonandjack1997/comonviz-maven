package au.uq.dke.comonviz.ui.dataUI;

import java.awt.BorderLayout;
import java.util.Set;

import javax.swing.JFrame;

import org.metawidget.swing.SwingMetawidget;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.data.DataModel;
import database.model.data.bussinesProcessManagement.ProcessActivity;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.model.data.bussinesProcessManagement.ProcessRule;
import database.service.GenericService;
import database.service.ServiceManager;

public class GenericDashboardDialog<R extends DataModel> extends JFrame {

	private static final long serialVersionUID = 1L;
	private Class<R> clazz;
	private SwingMetawidget buttonsWidget;


	public GenericDashboardDialog(Class<R> clazz) {

		this.clazz = clazz;
		this.setLayout(new BorderLayout());
		this.setVisible(true);

		//this.setModalityType(ModalityType.APPLICATION_MODAL);

		GenericTableUnit tableUnit = new GenericTableUnit(clazz);
		GenericTableUnit tableUnit2 = new GenericTableUnit(clazz);
		this.add(tableUnit, BorderLayout.CENTER);
		this.add(tableUnit2, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);
	}


	public void updateDashboard() {
		//update the main list

	}

	public static void main(String args[]) throws BeansException, InstantiationException, IllegalAccessException{
		
		Class<ProcessActivity> clazz = ProcessActivity.class;
		
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		GenericService service = ServiceManager.getGenericService(clazz);
		GenericService objectiveService = ServiceManager.getGenericService(ProcessObjective.class);
		
		
		//service.deleteAll();
		ProcessActivity p = (ProcessActivity) service.findAll().get(0);
		
		
		ProcessActivity pa1 = new ProcessActivity();
		Set<?> set = pa1.getProcessObjectives();
		pa1.setName("process activity 2");
		
		ProcessObjective o1 = new ProcessObjective();
		objectiveService.save(o1);
		
		pa1.getProcessObjectives().add(o1);
		service.save(pa1);

		new GenericDashboardDialog<ProcessRule>(ProcessRule.class);
		ctx.close();
		return;
	}
}
