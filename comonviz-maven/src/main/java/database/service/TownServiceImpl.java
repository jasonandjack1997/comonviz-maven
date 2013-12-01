package database.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;

import database.dao.TownDAO;
import database.model.Town;


@Service
@Transactional
public class TownServiceImpl implements TownService {

	TownDAO dao;
	
	@Autowired
	public void setDao(TownDAO dao) {
		this.dao = dao;
	}
	
	public void persist(Town town) {
		dao.save(town);
	}
	
	public List<Town> findAll() {
		return dao.findAll();
	}
	
	public Town findByName(String name) {
		return dao.searchUnique(new Search().addFilterEqual("name", name).addFetch("citizens"));
	}
}
