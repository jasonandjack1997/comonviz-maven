package unused;

import org.springframework.stereotype.Service;

import database.dao.TownDAO;
import database.model.Town;
import database.service.GenericService;

@Service
public class TownServiceGeneric extends GenericService<Town, TownDAO>{

}
