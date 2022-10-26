/**
 * 
 */
package com.arun.seecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arun.seecity.model.User;
import com.arun.seecity.security.AuthService;

/**
 * @author arun
 *
 */
@RestController
public class CommonController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthService authService;

	@GetMapping("ping")
	public String ping() {
		return "I feel so alive!";
	}

	@PostMapping("user/new")
	public String addUser(@RequestBody User user) {
		authService.saveUser(user);
		return "User " + user.getUsername() + " added successfully";
	}
}
