package com.arun.seecity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arun.seecity.model.BaseEntity;

/**
 * Repository to handle all the transactions of the <code>BaseEntity</code>
 * 
 * @author Arun S P
 *
 * @param <T>
 */
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
