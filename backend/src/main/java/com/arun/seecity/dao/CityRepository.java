package com.arun.seecity.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.arun.seecity.model.City;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {

	Page<City> findAll(Pageable pageable);

	Page<City> findByNameContainingIgnoreCase(String searchString, Pageable pageable);
}
