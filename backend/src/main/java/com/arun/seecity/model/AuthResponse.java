/**
 * 
 */
package com.arun.seecity.model;

/**
 * The happy response indicating that the Authentication was successful and that
 * we trust the request. And we will issue a token here that we expect to be
 * presented with in subsequent requests. We also return the User object, so
 * that the client can use it to display context.
 * 
 * @author Arun S P
 *
 */
public class AuthResponse {

	private String token;
	private User user;

	public AuthResponse(String token, User user) {
		this.token = token;
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
