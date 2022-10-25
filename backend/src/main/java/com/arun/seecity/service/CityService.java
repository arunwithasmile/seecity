package com.arun.seecity.service;

import java.util.List;

import com.arun.seecity.model.City;

public interface CityService {

	public List<City> getCities();

	public City getCity(long id);

}
