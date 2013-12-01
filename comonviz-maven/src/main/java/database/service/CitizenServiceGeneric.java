package database.service;

import org.springframework.stereotype.Service;

import database.dao.CitizenDAO;
import database.model.Citizen;

@Service
public class CitizenServiceGeneric extends GenericService<Citizen, CitizenDAO>{

}
