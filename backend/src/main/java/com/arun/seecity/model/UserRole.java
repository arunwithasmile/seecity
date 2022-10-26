/**
 * 
 */
package com.arun.seecity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author arun
 *
 */
@Entity(name = "USER_ROLES")
public class UserRole extends BaseEntity {

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

}
