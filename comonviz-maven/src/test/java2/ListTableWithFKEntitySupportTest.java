package java2;

import java.util.HashSet;

import javax.swing.JFrame;

import au.uq.dke.comonviz.ui.data.panel.BasicTablePanel;
import database.model.data.businessProcessManagement.ProcessActivity;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.service.ProcessActivityService;
import database.service.ProcessObjectiveService;
import database.service.ServiceManager;

public class ListTableWithFKEntitySupportTest {
	private static ProcessActivityService activityService;
	private static ProcessObjectiveService objectiveService;
	
	public static void main(String args[]){
		activityService = (ProcessActivityService) ServiceManager.getService(ProcessActivity.class);
		objectiveService = (ProcessObjectiveService) ServiceManager.getService(ProcessObjective.class);

		ProcessActivity activity1 = new ProcessActivity();
		activity1.setName("activity 1");
		activity1.setProcessObjectives(new HashSet<ProcessObjective>());
		activityService.save(activity1);

		ProcessObjective objective1 = new ProcessObjective();
		objective1.setName("objective 1");
		objective1.setProcessActivity(activity1);
		activity1.getProcessObjectives().add(objective1);


		objectiveService.save(objective1);
		
		BasicTablePanel tableUnit1 = new BasicTablePanel(activity1.getClass());
		
		JFrame jFrame = new JFrame();
		jFrame.add(tableUnit1);
		jFrame.pack();
		jFrame.setVisible(true);

	}
	

}
