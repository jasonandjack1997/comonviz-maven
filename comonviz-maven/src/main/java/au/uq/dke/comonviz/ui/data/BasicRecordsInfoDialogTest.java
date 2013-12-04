package au.uq.dke.comonviz.ui.data;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.panel.UnassociatedRecordsPanel;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.bussinesProcessManagement.ProcessActivity;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.model.data.bussinesProcessManagement.ProcessRule;
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


		ProcessActivity activity1 = new ProcessActivity("activity 1");
		activityService.save(activity1);

		
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");

		activity1.getProcessObjectives().add(objective1);
		objective1.setProcessActivity(activity1);
	
		activity1.getProcessObjectives().add(objective2);
		objective2.setProcessActivity(activity1);

		objectiveService.save(objective1);
		objectiveService.save(objective2);
		
		ProcessRule rule1 = new ProcessRule("rule 1");
		rule1.persist();
		
		ReflectionUtils.associatedRecords(rule1, activity1);
		
		
		
		new BasicRecordsInfoDialog(ProcessRule.class);
		
	}
	
	public static void main(String[] args) {
		new BasicRecordsInfoDialogTest().test();
	}

}
