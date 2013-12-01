package database.service;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.Citizen;
import database.model.Citizen2;
import database.model.Town;
import database.model.Town2;


/**
 * Hello world!
 *
 */
public class MyApp2 
{
	private static Citizen2Service citizenService;
	private static Town2Service townService;
	
    public static void main( String[] args )
    {
    	
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        citizenService = (Citizen2Service) ctx.getBean("citizen2Service");
        townService = (Town2Service) ctx.getBean("town2Service");
        
        
       	Town2 nottingham = new Town2("Nottingham", 126);
    	//nottingham.setCitizens(new HashSet<Citizen>());
    	townService.save(nottingham);
    	
    	Citizen2 butcher = new Citizen2("Tom Butcher", "butcher", nottingham);
    	nottingham.getCitizens().add(butcher);
    	
    	Citizen2 baker = new Citizen2("Dick Baker", "baker", nottingham);
    	nottingham.getCitizens().add(baker);
    	
    	Citizen2 chandler = new Citizen2("Harry Chandler", "candlestick maker", nottingham);
    	nottingham.getCitizens().add(chandler);
    	
    	citizenService.save(butcher);
    	citizenService.save(baker);
    	citizenService.save(chandler);
     	
    	for (Town2 town : townService.findAll()) {
    		town = townService.findByName(town.getName(), town.getClass());
			int a = 1;
    	}
    	
    	
    	for (Citizen2 citizen : citizenService.findAll()) {
    		System.out.println(" - " + citizen.getName() + " is a " + citizen.getJob());
    	}
    	
    	System.out.println("Done.");
    	
 //   	ctx.close();
    }
    
}
