/**
 * 
 */
package com.arun.seecity.controller;

import static com.arun.seecity.util.AppConstants.REST_DEFAULT_PAGE_SIZE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arun.seecity.model.City;
import com.arun.seecity.service.CityService;

/**
 * @author arun
 *
 */
@RestController
@RequestMapping("cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping
	public Page<City> getCities(@RequestParam(name = "search", required = false) String searchString,
			@PageableDefault(size = REST_DEFAULT_PAGE_SIZE, sort = "name") Pageable page) {
		List<City> cities = cityService.getCities();
		return new PageImpl<City>(cities, page, cities.size());
	}

	@GetMapping("{id}")
	public City getCity(@PathVariable("id") long id) {
		return cityService.getCity(id);
	}
}
