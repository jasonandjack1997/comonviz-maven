package au.uq.dke.comonviz;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.businessProcessManagement.ProcessActivity;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.model.data.businessProcessManagement.ProcessRule;
import database.service.GenericService;
import database.service.ServiceManager;

public class EntryPointTest {
	EntryPoint entryPoint = new EntryPoint();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEntryPointInit() {
		entryPoint.start();
	}

	public void testEntryPointWithData() {
		initData();
		entryPoint.start();
	}

	public static void initData() {
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
		

	}

	public static void main(String[] args) {
		new EntryPointTest().testEntryPointWithData();
	}

}
