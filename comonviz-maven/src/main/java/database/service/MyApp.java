package database.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.googlecode.genericdao.search.Search;

import database.model.Citizen;
import database.model.Town;


/**
 * Hello world!
 *
 */
public class MyApp 
{
	private static CitizenService citizenService;
	private static GenericService townService;
	private static GenericService generalDAO;
	
    public static void main( String[] args )
    {
    	
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        citizenService = (CitizenService) ctx.getBean("citizenServiceImpl");
        townService = (GenericService) ctx.getBean("townServiceGeneric");
        townService.setClass(Town.class);
        
        
    	initData();
    	
    	for (Town town : ((TownServiceGeneric)townService).findAll()) {
    		System.out.println(town.getName() + " (population " + town.getPopulation() + ")");
    		town = (Town) townService.findByName(town.getName());
    		for (Citizen citizen : town.getCitizens()) {
    			System.out.println(" - " + citizen.getName() + " is a " + citizen.getJob());
    		}
    	}
    	
    	System.out.println("Searching for citizens named Dick...");
    	
    	for (Citizen citizen : (List<Citizen>) generalDAO.search(new Search(Citizen.class).addFilterLike("name", "Dick%"))) {
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
