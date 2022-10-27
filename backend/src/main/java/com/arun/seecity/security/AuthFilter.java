/**
 * 
 */
package com.arun.seecity.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This also is a gatekeeper, but like an intern. It only checks for the token
 * we previously issued to a user who came with username and password. Now if we
 * find that the token they bring back is in fact a valid one, we let them in.
 * 
 * @author Arun S P
 *
 */
@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtHelperService jwtHelperService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Resolve the Token from the Header
		String jwt = jwtHelperService.resolveToken(request);
		// Check if it is valid
		if (jwtHelperService.isValid(jwt)) {
			Authentication auth = jwtHelperService.getAuthentication(jwt);
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}
}
