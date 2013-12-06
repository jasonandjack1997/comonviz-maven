package au.uq.dke.comonviz.utils;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import au.uq.dke.comonviz.graph.node.DefaultGraphNode;
import au.uq.dke.comonviz.misc.CustomRuntimeException;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import database.model.data.BasicRecord;
import database.model.ontology.OntologyClass;
import database.model.ontology.OntologyClass2;

public class DatabaseUtils {

	static ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	
	private static SessionFactory sessionFactory = ctx.getBean(SessionFactory.class);
	private static Session session = sessionFactory.openSession();
	
	public static Session getSession() {
		return session;
	}
	
	public static Object findById(Class recordType, Serializable id){
		return session.get(recordType, id);
		
	}
	public static String getModelClassPrefix(DefaultGraphNode graphNode){
		
		for(Object record: findAll(OntologyClass.class)){
			OntologyClass ontologyClass = (OntologyClass) record;
			if(ontologyClass.getId() == graphNode.getBranchId()){
				return ontologyClass.getName();
			}
		}
		
		throw new CustomRuntimeException("no branch name found");
		
	}
	public static String getTableNameByNodeName(String nodeName) {
		String tableName = nodeName.replace(" of ", "Of").replace(" ", "").replace("&", "And");
		return tableName;
	}

	
	public static List findAll(Class recordType){
		return session.createCriteria(recordType).list();
	}

	public static void importFromOldTable() {
		SessionFactory sf = ctx.getBean(SessionFactory.class);

		Session session = sf.openSession();

		session.beginTransaction();

		List classes = session.createCriteria(OntologyClass2.class).list();

		Query query = session.createSQLQuery("select ontologyClass.id as id, trackable.name, level, iri, discription, branchid from ontologyClass inner join trackable on ontologyClass.id=trackable.id");
//		Query query = session.createSQLQuery("select * from ontologyClass inner join trackable where ontologyClass.id = trackable.id").addEntity(OntologyClass2.class);

		List result = query.list();
		return;

	}

	public static void main(String[] args) {
		DatabaseUtils.importFromOldTable();
	}
}
