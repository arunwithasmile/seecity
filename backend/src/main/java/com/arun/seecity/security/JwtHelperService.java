/**
 * 
 */
package com.arun.seecity.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author arun
 *
 */
@Service
public interface JwtHelperService {

	static final String secret = "(ityS3cret";
	static final int EXPIRE_TIME_HRS = 8;

	public String generateToken(UserDetails userDetails, List<String> roles);

	public boolean isValid(String token);

	default String resolveToken(HttpServletRequest request) {
		final String autorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (autorizationHeader != null && autorizationHeader.startsWith("Bearer")) {
			return autorizationHeader.substring(7);
		}
		return null;
	}

	public Authentication getAuthentication(String token);
}
