package databaseTest.manyToMany.unidirectinal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public void testMapping() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		City c1 = new City();
		Store s1 = new Store();
		session.save(c1);

		s1.getImplantedIn().add(c1);
		session.save(s1);
		
		Query query = session.createSQLQuery(
				"select * from store")
				.addEntity(Store.class);
				List result = query.list();
		return;
	}

	public void testMappingUseService() {
		GenericService2 cityService = ServiceManager2.getGenericService2(City.class);
		GenericService2 storeService = ServiceManager2.getGenericService2(Store.class);

		City c1 = new City();
//		Store s1 = new Store();
		cityService.save(c1);

//		s1.getImplantedIn().add(c1);
//		storeService.save(s1);
		
		List stores = storeService.findAll();
		
		return;
		
	}

	public static void main(String[] args) {
		new Test().testMappingUseService();
	}
}
