package database.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.Activity;
import database.model.Objective;

public class TestOneToManyMappingUsingGenericService {

	private static ActivityService activityService;
	private static ObjectiveService objectiveService;

	public static void main(String args[]) {

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		activityService = (ActivityService) ctx.getBean("activityService");
		objectiveService = (ObjectiveService) ctx.getBean("objectiveService");

		Activity activity1 = new Activity();
		activity1.setName("activity 1");
		activity1.setObjectives(new HashSet<Objective>());
		activityService.save(activity1);

		Objective objective1 = new Objective();
		objective1.setName("objective 1");
		objective1.setActivity(activity1);
		activity1.getObjectives().add(objective1);


		objectiveService.save(objective1);

		for (Activity activity : activityService.findAll()) {
			activity = activityService.findByName(activity.getName(),
					activity.getClass());
			int a = 1;
		}

		List<Objective> objectives = objectiveService.findAll();

		return;

	}
}
