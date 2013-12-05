package database.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import au.uq.dke.comonviz.utils.DatabaseUtils;
import database.model.data.BigCity;
import database.model.data.City;
import database.model.data.Store;
import database.service.GenericService2;
import database.service.ServiceManager;

public class Test {

	public void testMapping() {
		// Session session = HibernateUtil.getSessionFactory().openSession();
		Session session = DatabaseUtils.getSession();
		// session.beginTransaction();

		City c1 = new City();
		Store s1 = new Store();
		session.save(c1);
		
		BigCity bc1 = new BigCity();
		bc1.setName("big city 1");
		session.save(bc1);
		

		s1.getImplantedIn().add(c1);
		session.save(s1);

		Query query = session.createSQLQuery("select * from store").addEntity(
				Store.class);
		List result = query.list();
		
		List result2 = DatabaseUtils.findAll(Store.class);
		
		List cityList = DatabaseUtils.findAll(City.class);

		return;
	}

	public void testMappingUseService() {
		GenericService2 cityService = ServiceManager
				.getGenericService2(City.class);
		GenericService2 storeService = ServiceManager
				.getGenericService2(Store.class);

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SessionFactory sf = ctx.getBean(SessionFactory.class);

		Session session = sf.openSession();
		session.beginTransaction();

		City c1 = new City();
		Store s1 = new Store();
		s1.setName("store 1");
		cityService.save(c1);

		s1.getImplantedIn().add(c1);
		storeService.save(s1);

		cityService.flush();
		storeService.flush();

		List<Store> stores = storeService.findAll();
		for (Store store : stores) {
			store = (Store) storeService.findByName(store.getName(),
					store.getClass());
		}

		Query query = session.createSQLQuery("select * from store").addEntity(
				Store.class);
		List result = query.list();
		return;

	}

	public static void main(String[] args) {
		new Test().testMapping();
	}
}
