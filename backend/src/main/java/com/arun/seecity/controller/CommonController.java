/**
 * 
 */
package com.arun.seecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
