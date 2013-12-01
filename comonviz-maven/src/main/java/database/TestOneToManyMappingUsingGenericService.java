package database;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.data.bussinesProcessManagement.Activity;
import database.model.data.bussinesProcessManagement.Objective;
import database.service.CitizenServiceGeneric;
import database.service.GenericService;
import database.service.ProcessActivityService;
import database.service.ProcessObjectiveService;
import database.service.ServiceManager;

public class TestOneToManyMappingUsingGenericService {

	public static void main(String args[]){
		
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
//        citizenService = (CitizenServiceGeneric) ctx.getBean("citizenServiceGeneric");
//        townService = (GenericService) ctx.getBean("townServiceGeneric");

        ProcessActivityService activityService = (ProcessActivityService) ctx.getBean("processActivityService");
		ProcessObjectiveService objectiveService = (ProcessObjectiveService) ctx.getBean("processObjectiveService");
//		ProcessActivityService activityService = (ProcessActivityService) ServiceManager.getService(ProcessActivity.class);
//		ProcessObjectiveService objectiveService = (ProcessObjectiveService) ServiceManager.getService(ProcessObjective.class);
		
		Activity activity1 = new Activity();
		activity1.setName("activity 1");
		activityService.save(activity1);
		
		Objective objective1 = new Objective();
		//objective1.setName("objective 1");
		Objective objective2 = new Objective();
		//objective2.setName("objective 2");
		
		activity1.getObjectives().add(objective1);
		activity1.getObjectives().add(objective2);
		
		objectiveService.save(objective1);
		objectiveService.save(objective2);
		
		for(Activity activity : activityService.findAll()){
			activity = activityService.findByName(activity.getName(), activity.getClass());
			int a = 1;
		}
		
		
		
		
		return;
		
	}
}
