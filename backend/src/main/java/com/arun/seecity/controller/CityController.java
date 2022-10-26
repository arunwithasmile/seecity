/**
 * 
 */
package com.arun.seecity.controller;

import static com.arun.seecity.util.AppConstants.REST_DEFAULT_PAGE_SIZE;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
			@PageableDefault(size = REST_DEFAULT_PAGE_SIZE, sort = "name") Pageable pageParams) {
		return cityService.getCities(searchString, pageParams);
	}

	@GetMapping("{id}")
	public City getCity(@PathVariable("id") long id) {
		return cityService.getCity(id);
	}

	@PostMapping("upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			cityService.bulkUpload(file.getInputStream());
		} catch (IOException e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save records: " + e.getMessage());
		}
		return ResponseEntity.ok("1000 records saved successfully");
	}
}
