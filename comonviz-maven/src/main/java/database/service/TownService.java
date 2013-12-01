package database.service;

import java.util.List;

import database.model.Town;


public interface TownService {

	public void persist(Town citizen);

	public List<Town> findAll();

	public Town findByName(String name);

}