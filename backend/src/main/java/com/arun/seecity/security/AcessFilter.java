/**
 * 
 */
package com.arun.seecity.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author arun
 *
 */
@Component
public class AcessFilter extends OncePerRequestFilter {

	@Autowired
	JwtHelperService jwtHelperService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = jwtHelperService.resolveToken(request);
		if (jwt != null && !jwt.isBlank()) {
			Authentication auth = jwtHelperService.getAuthentication(jwt);
			if (!hasCityEditRole(request, auth)) {
				response.sendError(HttpStatus.UNAUTHORIZED.value(), "User doesn't have access to edit the resourses");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean hasCityEditRole(HttpServletRequest request, Authentication auth) {
		if (request.getRequestURI().startsWith("/cities/") && request.getMethod().equals("POST")) {
			@SuppressWarnings("unchecked")
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals("admin") || authority.getAuthority().equals("ROLE_ALLOW_EDIT")) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
