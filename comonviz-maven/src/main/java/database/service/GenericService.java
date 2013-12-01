package database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import sample.googlecode.genericdao.model.Town;
import au.uq.dke.comonviz.model.OntologyModelListener;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

//@Service
@Transactional
public class GenericService<Model, DAO extends GenericDAO<Model, Long>> {

	DAO dao;

	public DAO getDao() {
		return dao;
	}
	List<OntologyModelListener> listeners = new ArrayList<OntologyModelListener>();
	protected void fireAxiomAddedEvent(Model model){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomAdded(model);
		}
	}

	protected void fireAxiomUpdatedEvent(Model model){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomUpdated(model);
		}
	}

	protected void fireAxiomRemovedEvent(Model model){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomRemoved(model);
		}
	}
	
	public void deleteAll() {
		List<Model> modelList = dao.findAll();
		for(Model model: modelList){
			dao.remove(model);
		}
	}
	
	public void delete(Model model){
		dao.remove(model);
		fireAxiomRemovedEvent(model);
	}

	@Autowired
	public void setDao(DAO dao) {
		this.dao = dao;
	}

	public void save(Model model) {
		boolean isCreate = dao.save(model);
		if (isCreate) {
			fireAxiomAddedEvent(model);
		} else {
			fireAxiomUpdatedEvent(model);
		}
	}

	public List<Model> findAll() {
		return dao.findAll();
	}

	public Model findByName(String name) {
		if (name == null)
			return null;
		return dao.searchUnique(new Search().addFilterEqual("name", name));
	}

	public void flush() {
		dao.flush();
	}

	public List<Model> search(ISearch search) {
		return dao.search(search);
	}

	public Model findById(Long id) {
		return dao.find(id);
	}

	public void delete(Long id) {
		dao.removeById(id);
	}

	public SearchResult<Model> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}
	public List<OntologyModelListener> getListeners() {
		return listeners;
	}

}

