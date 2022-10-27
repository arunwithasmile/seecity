/**
 * 
 */
package com.arun.seecity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arun.seecity.dao.UserRepository;
import com.arun.seecity.dao.UserRoleRepository;
import com.arun.seecity.model.AuthRequest;
import com.arun.seecity.model.User;
import com.arun.seecity.model.UserRole;

/**
 * @author arun
 *
 */
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private JwtHelperService jwtHelperService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public String authenticate(AuthRequest authRequest) {
		// Authenticate User
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		// If success, get User Details from DB and generate its token
		if (auth != null && auth.isAuthenticated()) {
			AuthUserDetails userDetails = (AuthUserDetails) userDetailsService
					.loadUserByUsername(authRequest.getUsername());
			String token = jwtHelperService.generateToken(userDetails, userDetails.getRoles());
			return token;
		}
		return null;
	}

	@Override
	public User getUser(String userName) {
		AuthUserDetails userDetails = (AuthUserDetails) userDetailsService.loadUserByUsername(userName);
		return userDetails.getUser();
	}

	@Override
	public void saveUser(User user) {
		user.setPasswordX(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(user.getRoles().get(0).getRole());
		userRoleRepository.save(userRole);
	}
}