/**
 * 
 */
package com.arun.seecity.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arun.seecity.dao.CityRepository;
import com.arun.seecity.model.City;

/**
 * @author arun
 *
 */
@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;

	@Override
	public Page<City> getCities(String searchString, Pageable pageParams) {
		if (searchString == null || searchString.isBlank()) {
			return cityRepository.findAll(pageParams);
		}
		return cityRepository.findByNameContainingIgnoreCase(searchString, pageParams);
	}

	@Override
	public City getCity(long id) {
		return cityRepository.findById(id).orElse(null);
	}

	@Override
	public City saveCity(City city) {
		return cityRepository.save(city);
	}

	public void bulkUpload(InputStream inputStream) {
		List<City> cities = getCities(inputStream);
		cityRepository.saveAll(cities);
	}

	private List<City> getCities(InputStream inputStream) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<City> cities = new ArrayList<>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				City city = new City(Long.parseLong(csvRecord.get("Id")), csvRecord.get("Name"),
						csvRecord.get("Photo"));
				cities.add(city);
			}

			return cities;
		} catch (IOException e) {
			throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
		}
	}
}
