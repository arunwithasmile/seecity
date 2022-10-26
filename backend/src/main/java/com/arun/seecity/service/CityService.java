package com.arun.seecity.service;

import java.io.InputStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arun.seecity.model.City;

public interface CityService {

	public Page<City> getCities(String searchString, Pageable pageParams);

	public City getCity(long id);

	public void bulkUpload(InputStream inputStream);

}
