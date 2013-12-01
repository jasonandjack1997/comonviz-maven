package database.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import database.dao.ProcessObjectiveDAO;
import database.model.data.bussinesProcessManagement.Objective;

@Service
@Transactional
public class ProcessObjectiveService extends GenericService<Objective, ProcessObjectiveDAO>{

}
