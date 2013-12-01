package unused;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import database.service.GenericService;

@Service
@Transactional
public class ProcessActivityService extends GenericService<Activity, ProcessActivityDAO>{

	public static void main(String args[]){
	}
}
