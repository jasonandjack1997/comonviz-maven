package database.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.googlecode.genericdao.dao.jpa.GeneralDAO;
import com.googlecode.genericdao.search.Search;

import database.model.Citizen;
import database.model.Town;
import database.model.ontology.OntologyAxiom;


/**
 * Hello world!
 *
 */
public class ExampleApp 
{
	private static CitizenService citizenService;
	private static TownService townService;
	private static GeneralDAO generalDAO;
	
    public static void main( String[] args )
    {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        //GenericService service = ServiceManager.getGenericService(OntologyAxiom.class);
    	
        citizenService = (CitizenService) ctx.getBean("citizenServiceImpl");
        townService = (TownService) ctx.getBean("townServiceImpl");
//        generalDAO = (GeneralDAO) ctx.getBean("generalDAOImpl");
        
        
    	initData();
    	
    	for (Town town : townService.findAll()) {
    		System.out.println(town.getName() + " (population " + town.getPopulation() + ")");
    		town = townService.findByName(town.getName());
    		for (Citizen citizen : town.getCitizens()) {
    			System.out.println(" - " + citizen.getName() + " is a " + citizen.getJob());
    		}
    	}
    	
    	System.out.println("Searching for citizens named Dick...");
    	
    	for (Citizen citizen : (List<Citizen>) generalDAO.search(new Search(Citizen.class).addFilterLike("name", "Dick%"))) {
    		System.out.println(" - " + citizen.getName() + " is a " + citizen.getJob());
    	}
    	
    	System.out.println("Done.");
    	
    	ctx.close();
    }
    
    private static void initData() {
    	Town nottingham = new Town("Nottingham", 126);
    	nottingham.setCitizens(new HashSet<Citizen>());
    	townService.persist(nottingham);
    	
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
