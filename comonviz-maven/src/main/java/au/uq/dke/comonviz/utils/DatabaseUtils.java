package au.uq.dke.comonviz.utils;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.ontology.OntologyClass;
import database.model.ontology.OntologyClass2;

public class DatabaseUtils {

	static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void importFromOldTable(){
		SessionFactory sf = ctx.getBean(SessionFactory.class);
		
		Session session = sf.openSession();
		
		session.beginTransaction();
		
		List classes = session.createCriteria(OntologyClass.class).list();
		
		return;
		
		
		
		
	}
	
	public static void main(String[] args) {
		DatabaseUtils.importFromOldTable();
	}
}
