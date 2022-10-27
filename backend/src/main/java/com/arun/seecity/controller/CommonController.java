/**
 * 
 */
package com.arun.seecity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A lonely Controller for miscellaneous purposes.
 * 
 * @author Arun S P
 *
 */
@RestController
public class CommonController {

	/**
	 * Hello there!
	 * 
	 * @return
	 */
	@GetMapping("ping")
	public String ping() {
		return "I feel so alive!";
	}
}
