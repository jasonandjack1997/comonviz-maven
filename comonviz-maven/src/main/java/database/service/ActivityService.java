package database.service;

import org.springframework.stereotype.Service;

import database.dao.ActivityDAO;
import database.model.data.bussinesProcessManagement.ProcessActivity;

@Service
public class ActivityService extends GenericService<ProcessActivity, ActivityDAO>{

}
