package database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.uq.dke.comonviz.model.OntologyModelListener;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

import database.dao.ProcessActivityDAO;
import database.model.data.bussinessProcessManagement.ProcessActivity;

@Service
@Transactional
public class ProcessActivityService {

	ProcessActivityDAO dao;

	List<OntologyModelListener> listeners = new ArrayList<OntologyModelListener>();
	protected void fireAxiomAddedEvent(ProcessActivity axiom){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomAdded(axiom);
		}
	}

	protected void fireAxiomUpdatedEvent(ProcessActivity axiom){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomUpdated(axiom);
		}
	}

	protected void fireAxiomRemovedEvent(ProcessActivity axiom){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomRemoved(axiom);
		}
	}

	public void deleteAll() {
		List<ProcessActivity> axiomList = dao.findAll();
		for(ProcessActivity axiom: axiomList){
			dao.remove(axiom);
		}
	}
	
	public void delete(ProcessActivity axiom){
		dao.remove(axiom);
		fireAxiomRemovedEvent(axiom);
	}

	@Autowired
	public void setDao(ProcessActivityDAO dao) {
		this.dao = dao;
	}

	public void save(ProcessActivity ontologyAxiom) {
		boolean isCreate = dao.save(ontologyAxiom);
		if (isCreate) {
			fireAxiomAddedEvent(ontologyAxiom);
		} else {
			fireAxiomUpdatedEvent(ontologyAxiom);
		}
	}

	public List<ProcessActivity> findAll() {
		return dao.findAll();
	}

	public ProcessActivity findByName(String name) {
		if (name == null)
			return null;
		return dao.searchUnique(new Search().addFilterEqual("name", name));
	}

	public void flush() {
		dao.flush();
	}

	public List<ProcessActivity> search(ISearch search) {
		return dao.search(search);
	}

	public ProcessActivity findById(Long id) {
		return dao.find(id);
	}

	public void delete(Long id) {
		dao.removeById(id);
	}

	public SearchResult<ProcessActivity> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}
	public List<OntologyModelListener> getListeners() {
		return listeners;
	}

}
