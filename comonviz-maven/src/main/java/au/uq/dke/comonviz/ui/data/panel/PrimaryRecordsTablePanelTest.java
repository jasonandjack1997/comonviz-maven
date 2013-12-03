package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.PrimaryRecordsTableModel;
import database.model.data.bussinesProcessManagement.ProcessActivity;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class PrimaryRecordsTablePanelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		JFrame frame = new JFrame();
		frame.add(new PrimaryRecordsTablePanel(ProcessActivity.class));
		frame.pack();
		frame.setVisible(true);
		return;
	}

	public static void main(String[] args) {
		GenericService activityService = ServiceManager
				.getGenericService(ProcessActivity.class);
		GenericService objectiveService = ServiceManager
				.getGenericService(ProcessObjective.class);

		ProcessActivity activity = new ProcessActivity("activity 1");
		activityService.save(activity);

		
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");

		activity.getObjectives().add(objective2);
		objective1.setActivity(activity);
		objective2.setActivity(activity);
		activity.getObjectives().add(objective1);

		objectiveService.save(objective1);
		objectiveService.save(objective2);

		new PrimaryRecordsTablePanelTest().test();

	}
}
