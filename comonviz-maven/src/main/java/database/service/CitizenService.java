package database.service;

import java.util.List;

import database.model.Citizen;


public interface CitizenService {

	public void save(Citizen citizen);

	public List<Citizen> findAll();

	public Citizen findByName(String name);

	public void flush();
}