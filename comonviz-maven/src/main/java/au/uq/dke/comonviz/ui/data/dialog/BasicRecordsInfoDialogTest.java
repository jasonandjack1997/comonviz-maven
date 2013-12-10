package au.uq.dke.comonviz.ui.data.dialog;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.panel.UnassociatedRecordsPanel;
import au.uq.dke.comonviz.utils.DatabaseUtils;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.businessProcessManagement.ProcessActivity;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.model.data.businessProcessManagement.ProcessRule;
import database.service.GenericService;
import database.service.ServiceManager;

public class BasicRecordsInfoDialogTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

		ProcessActivity activity1 = new ProcessActivity("activity 1");
		DatabaseUtils.getSession().save(activity1);

		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");

		activity1.getAssociatedProcessObjectives().add(objective1);

		activity1.getAssociatedProcessObjectives().add(objective2);

		DatabaseUtils.getSession().save(objective1);
		DatabaseUtils.getSession().save(objective2);

		new BasicRecordsInfoDialog(ProcessActivity.class);

	}

	public static void main(String[] args) {
		new BasicRecordsInfoDialogTest().test();
	}

}
