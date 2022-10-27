/**
 * 
 */
package com.arun.seecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arun.seecity.model.AuthRequest;
import com.arun.seecity.model.AuthResponse;
import com.arun.seecity.model.User;
import com.arun.seecity.security.AuthService;

/**
 * The gatekeeper. This handles all the authentications and user creations.
 * 
 * @author Arun S P
 *
 */
@RestController
@RequestMapping("auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	/**
	 * Authenticates a user using username & password.
	 * 
	 * @param authRequest Object containing the credentials
	 * @return JWT Token if the authentication was successful. Null otherwise.
	 */
	@PostMapping
	public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
		String token = authService.authenticate(authRequest);
		User user = authService.getUser(authRequest.getUsername());
		if (token != null && !token.isEmpty()) {
			return ResponseEntity.ok(new AuthResponse(token, user));
		}
		return null;
	}

	/**
	 * Adds a new User. Saves corresponding roles as well.
	 * 
	 * @param user - The object representing the user.
	 * @return
	 */
	@PostMapping("user/new")
	public String addUser(@RequestBody User user) {
		authService.saveUser(user);
		return "User " + user.getUsername() + " added successfully";
	}
}
