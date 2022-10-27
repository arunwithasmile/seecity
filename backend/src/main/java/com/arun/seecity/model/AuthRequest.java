/**
 * 
 */
package com.arun.seecity.model;

/**
 * This is the hopeful object that arrives at our door. We use this to decide if
 * we open our doors for the client.
 * 
 * @author Arun S P
 *
 */
public class AuthRequest {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
