package database.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import au.uq.dke.comonviz.model.OntologyModelListener;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

import database.model.data.BasicRecord;

//@Service
@Transactional
public class GenericService2<Model extends Object, DAO extends GenericDAO<Model, Long>> {

	DAO dao;
	Class<?> clazz;

	public DAO getDao() {
		return dao;
	}

	List<OntologyModelListener> listeners = new ArrayList<OntologyModelListener>();

	protected void fireAxiomAddedEvent(Model model) {
		for (OntologyModelListener listener : listeners) {
			listener.databaseAxiomAdded(model);
		}
	}

	protected void fireAxiomUpdatedEvent(Model model) {
		for (OntologyModelListener listener : listeners) {
			listener.databaseAxiomUpdated(model);
		}
	}

	protected void fireAxiomRemovedEvent(Model model) {
		for (OntologyModelListener listener : listeners) {
			listener.databaseAxiomRemoved(model);
		}
	}

	public void deleteAll() {
		List<Model> modelList = dao.findAll();
		for (Model model : modelList) {
			dao.remove(model);
		}
	}

	public void delete(Model model) {
		dao.remove(model);
		fireAxiomRemovedEvent(model);
	}

	public void setClass(Class<Model> clazz) {
		this.clazz = clazz;
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
		List<Model> results = dao.findAll();
		return results;
	}

	public Model findByName(String name, Class<?> clazz) {

		if (name == null)
			return null;

		Search search = new Search();
		search.addFilterEqual("name", name);

		for (Field field : clazz.getDeclaredFields()) {
			if (Set.class.isAssignableFrom(field.getType())) {
				search.addFetch(field.getName());
			}
		}
		return dao.searchUnique(search);
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
