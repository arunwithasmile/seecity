/**
 * 
 */
package com.arun.seecity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author arun
 *
 */
@RestController
public class CommonController {

	@GetMapping("ping")
	public String ping() {
		return "I feel so alive!";
	}
}
