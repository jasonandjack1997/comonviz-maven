package database.service;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import database.dao.ProcessActivityDAO;
import database.model.data.bussinesProcessManagement.ProcessActivity;

@Service
public class ProcessActivityService extends GenericService<ProcessActivity, ProcessActivityDAO>{

	public static void main(String args[]){
		ConfigurableApplicationContext ctx;
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//		ProcessActivityService processActivityService = (ProcessActivityService) ctx.getBean(ProcessActivityService.class.getSimpleName().substring(0, 1).toLowerCase() + ProcessActivityService.class.getSimpleName().substring(1));
		ProcessActivityService processActivityService = (ProcessActivityService) ctx.getBean(new ProcessActivity().getServiceName());
		
		ProcessActivity pa1 = new ProcessActivity();
		pa1.setName("pa1");
		processActivityService.save(pa1);
		
		List paList = processActivityService.findAll();
		ctx.close();
	}
}
