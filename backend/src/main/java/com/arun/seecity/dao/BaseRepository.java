package com.arun.seecity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.seecity.model.BaseEntity;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
