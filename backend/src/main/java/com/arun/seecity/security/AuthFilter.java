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
 * @author arun
 *
 */
@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtHelperService jwtHelperService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = jwtHelperService.resolveToken(request);
		if (jwtHelperService.isValid(jwt)) {
			Authentication auth = jwtHelperService.getAuthentication(jwt);
			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
	}
}
