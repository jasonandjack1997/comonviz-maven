package database.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import database.model.Stock;
import database.model.StockDailyRecord;


public class App {
	public static void main(String[] args) {

		testOneToMany();
	}

	public static void testOneToMany() {
		System.out.println("Hibernate one to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		@SuppressWarnings("unchecked")
//		List<Stock> stocks = session.createQuery("select s from stock s")
//				.list();
		// List<Stock> stocks = session.createCriteria(Stock.class).list();

		Stock stock = new Stock();
		stock.setStockCode("7052");
		stock.setStockName("PADINI");
		session.save(stock);

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
		
		
		
		////////////
	      session = HibernateUtil.getSessionFactory().openSession();
	      Transaction  tx = null;
	      try{
	         tx = session.beginTransaction();
	         List Stocks = session.createQuery("FROM Stock").list(); 
	         for (Iterator iterator1 = 
	                           Stocks.iterator(); iterator1.hasNext();){
	            Stock stock2 = (Stock) iterator1.next(); 
	            Set records = stock2.getStockDailyRecords();
	            for (Iterator iterator2 = 
	                         records.iterator(); iterator2.hasNext();){
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

	}
	
	
}
