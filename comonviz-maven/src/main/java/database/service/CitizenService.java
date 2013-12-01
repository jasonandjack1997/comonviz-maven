package database.service;

import org.springframework.stereotype.Service;

import database.dao.CitizenDAO;
import database.model.Citizen;

@Service
public class CitizenService extends GenericService<Citizen, CitizenDAO>{

}
