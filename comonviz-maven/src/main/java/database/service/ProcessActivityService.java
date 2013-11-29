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
	}
}
