package database.service;

import org.springframework.stereotype.Service;

import database.dao.CityDAO;
import database.model.data.City;
import database.service.GenericService;

@Service
public class CityService extends GenericService2<City, CityDAO>{

}
