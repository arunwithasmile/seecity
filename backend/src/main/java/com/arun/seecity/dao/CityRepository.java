package com.arun.seecity.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.arun.seecity.model.City;

/**
 * Reposity to handle all the transactions of the <code>City</code> entity.
 * 
 * @author Arun S P
 *
 */
public interface CityRepository extends PagingAndSortingRepository<City, Long> {

	/**
	 * Returns all the records matching the page criteria.
	 */
	Page<City> findAll(Pageable pageable);

	/**
	 * Returns all the records matching the page criteria, along with a search
	 * string.
	 * 
	 * @param searchString String to match records by name
	 * @param pageable     Pagination criteria
	 * @return matching records in paginated form
	 */
	Page<City> findByNameContainingIgnoreCase(String searchString, Pageable pageable);
}
