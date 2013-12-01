package database.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import database.dao.StockDAO;
import database.model.Stock;
import database.model.StockDailyRecord;

@Service
@Transactional
public class StockService extends GenericService<Stock, StockDAO> {

	public static void main(String args[]) {
		StockDailyRecordService stockDailyRecordService = (StockDailyRecordService) ServiceManager
				.getService(StockDailyRecord.class);
		StockService stockService = (StockService) ServiceManager
				.getService(Stock.class);

		//stockService.deleteAll();
		
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) ctx
				.getBean("sessionFactory");

		Session session = sessionFactory.openSession();

		session.beginTransaction();

		@SuppressWarnings("unchecked")

		Stock stock = new Stock();
		stock.setStockCode("7052");
		stock.setStockName("PADINI");
		//session.save(stock);
		stockService.save(stock);

		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());

		// stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);
		session.save(stockDailyRecords);
		session.getTransaction().commit();
		System.out.println("Done");
		
		Set records = stockService.findAll().get(0).getStockDailyRecords();

		
	      //session = HibernateUtil.getSessionFactory().openSession();
	      Transaction  tx = null;
	      try{
	         tx = session.beginTransaction();
	         List stocks = session.createQuery("FROM Stock").list(); 
	         for (Iterator iterator1 = 
	                           stocks.iterator(); iterator1.hasNext();){
	            Stock stock2 = (Stock) iterator1.next(); 
	            Set records1 = stock2.getStockDailyRecords();
	            for (Iterator iterator2 = 
	                         records1.iterator(); iterator2.hasNext();){
	                  StockDailyRecord record = (StockDailyRecord) iterator2.next(); 
	                  //System.out.println("StockDailyRecord: " + record.getName()); 
	            }
	         }
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }

	      return;
	}

}
