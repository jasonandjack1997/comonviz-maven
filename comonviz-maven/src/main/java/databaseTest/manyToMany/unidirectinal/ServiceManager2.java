package databaseTest.manyToMany.unidirectinal;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceManager2 {
	
	static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextTest.xml");

	@SuppressWarnings({ "rawtypes", "unused" })
	
	public static GenericService2 getGenericService2(Class<?> modelClass){
		return (GenericService2) ctx.getBean(getServiceName(modelClass));
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
