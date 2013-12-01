package java2;

import java.util.HashSet;

import javax.swing.JFrame;

import au.uq.dke.comonviz.ui.dataUI.BasicTableUnit;
import database.model.data.bussinesProcessManagement.ProcessActivity;
import database.model.data.bussinesProcessManagement.ProcessObjective;
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
		activity1.setObjectives(new HashSet<ProcessObjective>());
		activityService.save(activity1);

		ProcessObjective objective1 = new ProcessObjective();
		objective1.setName("objective 1");
		objective1.setActivity(activity1);
		activity1.getObjectives().add(objective1);


		objectiveService.save(objective1);
		
		BasicTableUnit tableUnit1 = new BasicTableUnit(activity1.getClass());
		
		JFrame jFrame = new JFrame();
		jFrame.add(tableUnit1);
		jFrame.pack();
		jFrame.setVisible(true);

	}
	

}
