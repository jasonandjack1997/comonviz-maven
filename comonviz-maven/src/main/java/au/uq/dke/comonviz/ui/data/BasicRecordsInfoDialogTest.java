package au.uq.dke.comonviz.ui.data;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.panel.UnassociatedRecordsPanel;
import database.model.data.bussinesProcessManagement.ProcessActivity;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class BasicRecordsInfoDialogTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		GenericService activityService = ServiceManager
				.getGenericService(ProcessActivity.class);
		GenericService objectiveService = ServiceManager
				.getGenericService(ProcessObjective.class);

		ProcessActivity activity = new ProcessActivity("activity 1");
		activityService.save(activity);

		
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");

		activity.getProcessObjectives().add(objective1);
		objective1.setProcessActivity(activity);
	
		activity.getProcessObjectives().add(objective2);
		objective2.setProcessActivity(activity);

		objectiveService.save(objective1);
		objectiveService.save(objective2);
		
		new BasicRecordsInfoDialog(ProcessActivity.class);
		
	}
	
	public static void main(String[] args) {
		new BasicRecordsInfoDialogTest().test();
	}

}
