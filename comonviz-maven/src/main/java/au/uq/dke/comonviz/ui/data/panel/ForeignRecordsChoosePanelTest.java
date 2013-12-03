package au.uq.dke.comonviz.ui.data.panel;

import javax.swing.JFrame;
import javax.swing.JTable;

import org.junit.Before;
import org.junit.Test;

import au.uq.dke.comonviz.ui.data.tableModel.PrimaryRecordsTableModel;
import database.model.data.bussinesProcessManagement.ProcessActivity;
import database.model.data.bussinesProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class ForeignRecordsChoosePanelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		ProcessObjective objective1 = new ProcessObjective("objective 1");
		ProcessObjective objective2 = new ProcessObjective("objective 2");
		GenericService objectiveService = ServiceManager.getGenericService(ProcessObjective.class);
		objectiveService.save(objective1);
		objectiveService.save(objective2);
		ProcessActivity activity = new ProcessActivity("activity 1");
		
		JFrame frame = new JFrame();
		frame.add(new ForeignRecordsChoosePanel(activity, ProcessObjective.class, null));
		frame.pack();
		frame.setVisible(true);
		
		return;
	}
	
	public static void main(String args[]){
		new ForeignRecordsChoosePanelTest().test();
	}

}
