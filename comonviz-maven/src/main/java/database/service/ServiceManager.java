package database.service;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceManager {
	
	static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	@SuppressWarnings({ "rawtypes", "unused" })
	
	public static GenericService getGenericService(Class<?> modelClass){
		return (GenericService) ctx.getBean(getServiceName(modelClass));
	}
	
	public static Object getService(Class<?> modelClass){
		return ctx.getBean(getServiceName(modelClass));
	}

	private static String getServiceName(Class<?> modelClass){
		String serviceName = modelClass.getSimpleName() + "Service";
		serviceName = serviceName.substring(0,1).toLowerCase() + serviceName.substring(1);
		return serviceName;

	}

}
