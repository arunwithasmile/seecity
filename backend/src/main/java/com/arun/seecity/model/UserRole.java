/**
 * 
 */
package com.arun.seecity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Roles define what level of access does a user hold in our app. Admin, for
 * instance can reach everything the sunlight touches. Some users are just here
 * to browse. They will have READ access only.
 * 
 * @author Arun S P
 *
 */
@Entity(name = "USER_ROLES")
public class UserRole extends BaseEntity {

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_ROLES"))
	private User user;

	private String role;

	@Column(length = 1024)
	private String description;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserRole() {
	}

	public UserRole(String role) {
		this.role = role;
	}

}
