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
 * This is a parent entity, as most entities tend to have something common in
 * them. Don't we all? id, created time, deleted status etc. are common among
 * all the entities.
 * 
 * @author Arun S P
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
