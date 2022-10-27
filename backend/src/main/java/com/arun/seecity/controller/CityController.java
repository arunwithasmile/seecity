/**
 * 
 */
package com.arun.seecity.controller;

import static com.arun.seecity.util.AppConstants.REST_DEFAULT_PAGE_SIZE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.seecity.model.City;
import com.arun.seecity.service.CityService;

/**
 * Headquarters of the App. Controller to handle all the requests related to
 * City entity.
 * 
 * @author Arun S P
 *
 */
@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityService cityService;

	/**
	 * Gets all the cities matching a search string limited to pagination specified.
	 * 
	 * @param searchString String to search Cities by the name.
	 * @param pageParams   Parameters to specify pagination, sorting etc.
	 * @return Paginated list of the queried Cities.
	 */
	@GetMapping
	public Page<City> getCities(@RequestParam(name = "search", required = false) String searchString,
			@PageableDefault(size = REST_DEFAULT_PAGE_SIZE, sort = "name") Pageable pageParams) {
		return cityService.getCities(searchString, pageParams);
	}

	/**
	 * Get a single City record by id
	 * 
	 * @param id The ID to be matched with
	 * @return Matched City or null otherwise
	 */
	@GetMapping("{id}")
	public City getCity(@PathVariable("id") long id) {
		return cityService.getCity(id);
	}

	/**
	 * Saves a particular City record in the system. This acts both as Add & Update.
	 * It creates a new record if the record (identified by id) does not exist, or
	 * updates it if it does.
	 * 
	 * @param id   Identifier of the City record.
	 * @param city The record itself.
	 * @return The saved record back to the caller. Coz why not?
	 */
	@PostMapping("{id}")
	public City saveCity(@PathVariable("id") long id, @RequestBody City city) {
		return cityService.saveCity(city);
	}
}
