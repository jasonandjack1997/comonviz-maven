package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.utils.DatabaseUtils;
import database.model.data.businessProcessManagement.ProcessActivity;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class UnassociatedRecordsPanelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		ProcessActivity activity = new ProcessActivity("activity 1");
		DatabaseUtils.getSession().save(activity);

		
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");

		activity.getProcessObjectives().add(objective1);
	
		//activity.getObjectives().add(objective2);
		//objective2.setActivity(activity);

		DatabaseUtils.getSession().save(objective1);
		DatabaseUtils.getSession().save(objective2);
		
		JFrame frame = new JFrame();
		frame.add(new UnassociatedRecordsPanel(ProcessObjective.class, ProcessActivity.class));
		frame.pack();
		frame.setVisible(true);
		
		
	}

	public static void main(String[] args) {
		new UnassociatedRecordsPanelTest().test();
	}
}
