package au.uq.dke.comonviz.ui.data.dialog;

import javax.swing.JDialog;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import database.model.data.bussinesProcessManagement.ProcessActivity;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class PrimeryRecordBeanDialogTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");
		GenericService objectiveService = ServiceManager
				.getGenericService(ProcessObjective.class);
		objectiveService.save(objective1);
		objectiveService.save(objective2);

		ProcessActivity activity = new ProcessActivity("activity 1");

		activity.getObjectives().add(objective2);
		objective1.setActivity(activity);
		objective2.setActivity(activity);
		activity.getObjectives().add(objective1);

		BasicTablePanel caller = new BasicTablePanel();
		JDialog dialog = new PrimeryRecordBeanDialog(activity, false, caller);
		
		return;
	}
	
	public static void main(String args[]){
		new PrimeryRecordBeanDialogTest().test();
	}
	

}
