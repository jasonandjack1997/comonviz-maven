package au.uq.dke.comonviz.ui.data.panel;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.tableModel.AssociatedRecordsTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.RecordsTableModel;
import au.uq.dke.comonviz.utils.DatabaseUtils;
import database.model.data.BasicRecord;
import database.model.data.businessProcessManagement.ProcessActivity;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class AssociatedRecordsPanelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");
		
		DatabaseUtils.getSession().save(objective1);
		DatabaseUtils.getSession().save(objective2);

		ProcessActivity activity = new ProcessActivity("activity 1");

		activity.getAssociatedProcessObjectives().add(objective2);
		activity.getAssociatedProcessObjectives().add(objective1);


		JFrame frame = new JFrame();
		frame.add(new AssociatedRecordsPanel(activity, activity.getAssociatedProcessObjectives(), ProcessObjective.class));
		frame.pack();
		frame.setVisible(true);

		return;
	}

	public static void main(String args[]) {
		new AssociatedRecordsPanelTest().test();
	}

}
