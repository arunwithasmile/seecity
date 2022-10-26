/**
 * 
 */
package com.arun.seecity.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author arun
 *
 */
@Entity(name = "CITIES")
public class City extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column(length = 2048)
	private String photoUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public City() {
	}

	public City(long id, String name, String photoUrl) {
		super(id);
		this.name = name;
		this.photoUrl = photoUrl;
	}

}
