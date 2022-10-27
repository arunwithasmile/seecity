/**
 * 
 */
package com.arun.seecity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arun.seecity.dao.UserRepository;
import com.arun.seecity.model.User;
import com.arun.seecity.security.AuthUserDetails;

/**
 * Service Class to provide the User Details from database to the Spring
 * Security Context.
 * 
 * @author Arun S P
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findOneByUsername(username);

		if (user != null) {
			return new AuthUserDetails(user);
		}
		return null;
	}
}
