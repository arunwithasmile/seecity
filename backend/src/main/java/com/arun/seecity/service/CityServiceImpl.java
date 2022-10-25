/**
 * 
 */
package com.arun.seecity.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.arun.seecity.model.City;

/**
 * @author arun
 *
 */
@Service
public class CityServiceImpl implements CityService {

	private Map<Long, City> cities = new HashMap<>();

	public CityServiceImpl() {
		cities.put(1L, new City(1, "London", "lndn.com"));
		cities.put(2L, new City(2, "New York", "ny.com"));
		cities.put(3L, new City(3, "Bengaluru", "blr.com"));
	}

	public List<City> getCities() {
		return new ArrayList<City>(cities.values());
	}

	public City getCity(long id) {
		return cities.get(id);
	}

}
