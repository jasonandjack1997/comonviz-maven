package au.uq.dke.comonviz.utils;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.ontology.OntologyClass;
import database.model.ontology.OntologyClass2;

public class DatabaseUtils {

	static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	public static void importFromOldTable() {
		SessionFactory sf = ctx.getBean(SessionFactory.class);

		Session session = sf.openSession();

		session.beginTransaction();

		List classes = session.createCriteria(OntologyClass2.class).list();

		Query query = session.createSQLQuery("select * from ontologyClass").addEntity(OntologyClass2.class);

		List result = query.list();
		return;

	}

	public static void main(String[] args) {
		DatabaseUtils.importFromOldTable();
	}
}
