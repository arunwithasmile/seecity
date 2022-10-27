/**
 * 
 */
package com.arun.seecity.security;

import org.springframework.stereotype.Service;

import com.arun.seecity.model.AuthRequest;
import com.arun.seecity.model.User;

/**
 * @author arun
 *
 */
@Service
public interface AuthService {

	public String authenticate(AuthRequest authRequest);

	public User getUser(String userName);

	public void saveUser(User user);
}
