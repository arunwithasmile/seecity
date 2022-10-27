package com.arun.seecity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arun.seecity.model.City;

/**
 * All the business logic on City entity. This also acts as a middle-man between
 * controller and the repository.
 * 
 * @author Arun S P
 *
 */
public interface CityService {

	/**
	 * Get the cities matching the search string and pagination criteria.
	 * 
	 * @param searchString Search string to search by name.
	 * @param pageParams   pagination criteria.
	 * @return Paginated list of matched cities.
	 */
	public Page<City> getCities(String searchString, Pageable pageParams);

	/**
	 * Gets a single City by id.
	 * 
	 * @param id ID to be matched with.
	 * @return Matched City record. Null otherwise.
	 */
	public City getCity(long id);

	/**
	 * Saving a City record. This either creates or updates based on whether the
	 * record already exists.
	 * 
	 * @param city Record to be saved.
	 * @return Saved record.
	 */
	public City saveCity(City city);

}
