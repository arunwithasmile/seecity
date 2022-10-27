/**
 * 
 */
package com.arun.seecity.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User Entity that logs in and makes transactions on City entity. Sometimes on
 * itself too.
 * 
 * @author Arun S P
 *
 */
@Entity(name = "USERS")
public class User extends BaseEntity {

	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	@JsonIgnore
	private String passwordX;

	@Transient
	private String password;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private List<UserRole> roles;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordX() {
		return passwordX;
	}

	public void setPasswordX(String passwordX) {
		this.passwordX = passwordX;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}

	public List<UserRole> getRoles() {
		return roles;
	}

	public String getPassword() {
		return password;
	}

	public User() {
	}

	public User(String firstName, String lastName, String username, String password, List<UserRole> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

}
