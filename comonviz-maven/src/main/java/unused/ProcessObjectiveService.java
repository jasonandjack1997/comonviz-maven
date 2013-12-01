package unused;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import database.service.GenericService;

@Service
@Transactional
public class ProcessObjectiveService extends GenericService<Objective, ProcessObjectiveDAO>{

}
