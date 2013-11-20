package database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.uq.dke.comonviz.model.DatabaseModelListener;

import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

import database.dao.OntologyAxiomDAO;
import database.model.ontology.OntologyAxiom;

/**
 * This is the implementation for our OntologyAxiom Service. The @Service
 * annotation allows Spring to automatically detect this as a component rather
 * than having to comfigure it in XML. The @Autowired annotation tells Spring to
 * inject our OntologyAxiom DAO using the setDao() method.
 * 
 * @author dwolverton
 * 
 */
@Service
@Transactional
public class OntologyAxiomService {

	OntologyAxiomDAO dao;

	List<DatabaseModelListener> listeners = new ArrayList<DatabaseModelListener>();
	protected void fireAxiomAddedEvent(OntologyAxiom axiom){
		for(DatabaseModelListener listener: listeners){
			listener.databaseAxiomAdded(axiom);
		}
	}

	protected void fireAxiomUpdatedEvent(OntologyAxiom axiom){
		for(DatabaseModelListener listener: listeners){
			listener.databaseAxiomUpdated(axiom);
		}
	}

	protected void fireAxiomRemovedEvent(OntologyAxiom axiom){
		for(DatabaseModelListener listener: listeners){
			listener.databaseAxiomRemoved(axiom);
		}
	}

	public void deleteAll() {
		List<OntologyAxiom> axiomList = dao.findAll();
		for(OntologyAxiom axiom: axiomList){
			dao.remove(axiom);
		}
	}
	
	public void delete(OntologyAxiom axiom){
		dao.remove(axiom);
		fireAxiomRemovedEvent(axiom);
	}

	@Autowired
	public void setDao(OntologyAxiomDAO dao) {
		this.dao = dao;
	}

	public void save(OntologyAxiom ontologyAxiom) {
		boolean isCreate = dao.save(ontologyAxiom);
		if (isCreate) {
			fireAxiomAddedEvent(ontologyAxiom);
		} else {
			fireAxiomUpdatedEvent(ontologyAxiom);
		}
	}

	public List<OntologyAxiom> findAll() {
		return dao.findAll();
	}

	public OntologyAxiom findByName(String name) {
		if (name == null)
			return null;
		return dao.searchUnique(new Search().addFilterEqual("name", name));
	}

	public void flush() {
		dao.flush();
	}

	public List<OntologyAxiom> search(ISearch search) {
		return dao.search(search);
	}

	public OntologyAxiom findById(Long id) {
		return dao.find(id);
	}

	public void delete(Long id) {
		dao.removeById(id);
	}

	public SearchResult<OntologyAxiom> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}
	public List<DatabaseModelListener> getListeners() {
		return listeners;
	}

}
