/**
 * 
 */
package com.arun.seecity.security;

import org.springframework.stereotype.Service;

import com.arun.seecity.model.AuthRequest;
import com.arun.seecity.model.User;

/**
 * Service to handle all the operations relating to Authentication. Like police
 * work.
 * 
 * @author Arun S P
 *
 */
@Service
public interface AuthService {

	/**
	 * Initial Authentication using the username/password pair. If successful, we
	 * issue a token to the caller and tell them to bring it back in subsequent
	 * requests.
	 * 
	 * @param authRequest Request holding the credentials.
	 * @return A valid generated token
	 */
	public String authenticate(AuthRequest authRequest);

	/**
	 * Gets a user by username. We know usernames are unique, so there's at most one
	 * result to this call
	 * 
	 * @param userName Field holding the username of the User.
	 * @return User object if the match was successful. Null otherwise
	 */
	public User getUser(String userName);

	/**
	 * Welcoming new users onboard. We create a new user with their password, and
	 * roles.
	 * 
	 * @param user User to be saved.
	 */
	public void saveUser(User user);
}
