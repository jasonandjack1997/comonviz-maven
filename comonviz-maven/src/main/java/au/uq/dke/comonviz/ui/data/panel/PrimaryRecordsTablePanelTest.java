package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.table.BasicTable;
import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import au.uq.dke.comonviz.utils.DatabaseUtils;
import database.model.data.businessProcessManagement.ProcessActivity;
import database.model.data.businessProcessManagement.ProcessObjective;
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

		ProcessActivity activity = new ProcessActivity("activity 1");
		DatabaseUtils.getSession().save(activity);

		
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");

		activity.getProcessObjectives().add(objective2);
		activity.getProcessObjectives().add(objective1);

		DatabaseUtils.getSession().save(objective1);
		DatabaseUtils.getSession().save(objective2);

		new PrimaryRecordsTablePanelTest().test();

	}
}
