package database.service;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import database.dao.ProcessActivityDAO;
import database.model.data.bussinessProcessManagement.ProcessActivity;

@Service
@Transactional
public class ProcessActivityService extends GenericServiceTest<ProcessActivity, ProcessActivityDAO>{

	public static void main(String args[]){
		ConfigurableApplicationContext ctx;
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProcessActivityService processActivityService = (ProcessActivityService) ctx.getBean("processActivityService");
		
		ProcessActivity pa1 = new ProcessActivity();
		pa1.setName("pa1");
		processActivityService.save(pa1);
		
		List paList = processActivityService.findAll();
		ctx.close();
	}
}
