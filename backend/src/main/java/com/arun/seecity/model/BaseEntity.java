/**
 * 
 */
package com.arun.seecity.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Where;

/**
 * @author arun
 *
 */
@MappedSuperclass
@Where(clause = "DELETED = false")
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private boolean deleted;

	public BaseEntity() {
	}

	public BaseEntity(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
