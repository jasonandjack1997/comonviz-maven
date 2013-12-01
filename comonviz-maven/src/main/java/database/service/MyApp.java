package database.service;

import java.util.HashSet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.Citizen;
import database.model.Town;


/**
 * Hello world!
 *
 */
public class MyApp 
{
	private static CitizenServiceGeneric citizenService;
	private static GenericService townService;
	private static GenericService generalDAO;
	
    public static void main( String[] args )
    {
    	
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        citizenService = (CitizenServiceGeneric) ctx.getBean("citizenServiceGeneric");
        townService = (TownServiceGeneric) ctx.getBean("townServiceGeneric");
        
    	initData();
    	
    	for (Town town : ((TownServiceGeneric)townService).findAll()) {
    		System.out.println(town.getName() + " (population " + town.getPopulation() + ")");
    		town = ((TownServiceGeneric)townService).findByName(town.getName(), town.getClass());
    		for (Citizen citizen : town.getCitizens()) {
    			System.out.println(" - " + citizen.getName() + " is a " + citizen.getJob());
    		}
    	}
    	
    	
    	for (Citizen citizen : citizenService.findAll()) {
    		System.out.println(" - " + citizen.getName() + " is a " + citizen.getJob());
    	}
    	
    	System.out.println("Done.");
    	
 //   	ctx.close();
    }
    
    private static void initData() {
    	Town nottingham = new Town("Nottingham", 126);
    	nottingham.setCitizens(new HashSet<Citizen>());
    	townService.save(nottingham);
    	
    	Citizen butcher = new Citizen("Tom Butcher", "butcher", nottingham);
    	nottingham.getCitizens().add(butcher);
    	
    	Citizen baker = new Citizen("Dick Baker", "baker", nottingham);
    	nottingham.getCitizens().add(baker);
    	
    	Citizen chandler = new Citizen("Harry Chandler", "candlestick maker", nottingham);
    	nottingham.getCitizens().add(chandler);
    	
    	citizenService.save(butcher);
    	citizenService.save(baker);
    	citizenService.save(chandler);
  }
}
