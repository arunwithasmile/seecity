/**
 * 
 */
package com.arun.seecity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.arun.seecity.AppException;

/**
 * Parent REST Controller. All Controllers must extend this, so that all the
 * common fuctionalities such as exception handling can be written here.
 * 
 * @author Arun S P
 *
 */
public class BaseController {

//	@ExceptionHandler
	public ResponseEntity<String> exception(AppException exception) {
		String error = new StringBuilder().append("Internal Server Error [").append(exception.getCode()).append("] :")
				.append(exception.getMessage()).toString();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}
}
