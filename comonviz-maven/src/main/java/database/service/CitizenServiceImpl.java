package database.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;

import database.dao.CitizenDAO;
import database.model.Citizen;

@Service
@Transactional
public class CitizenServiceImpl implements CitizenService {

	CitizenDAO dao;
	
	@Autowired
	public void setDao(CitizenDAO dao) {
		this.dao = dao;
	}
	
	public void save(Citizen citizen) {
		dao.save(citizen);
	}
	
	public List<Citizen> findAll() {
		return dao.findAll();
	}
	
	public Citizen findByName(String name) {
		if (name == null)
			return null;
		return dao.searchUnique(new Search().addFilterEqual("name", name));
	}
	
	public void flush() {
		dao.flush();
	}
}
