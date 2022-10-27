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
 * This filter is like the bouncer in the party. The user is in, but he has
 * access only certain areas, like the dance floor. DJ's table is off the
 * limits. This filter checks if a user has access to a particular URL Endpoint
 * or not. If so, it will let it pass. If not, it will be sent back.
 * 
 * @author Arun S P
 *
 */
@Component
public class AccessFilter extends OncePerRequestFilter {

	@Autowired
	JwtHelperService jwtHelperService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Getting the details of the users using the token
		String jwt = jwtHelperService.resolveToken(request);
		if (jwt != null && !jwt.isEmpty()) {
			Authentication auth = jwtHelperService.getAuthentication(jwt);
			// Currently implemented for EDIT access on City entity
			if (!hasCityEditRole(request, auth)) {
				response.sendError(HttpStatus.UNAUTHORIZED.value(), "User doesn't have access to edit the resourses");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean hasCityEditRole(HttpServletRequest request, Authentication auth) {
		// Basically an endpoint and the Method specify the operation on the entity.
		if (request.getRequestURI().startsWith("/cities/") && request.getMethod().equals("POST")) {
			@SuppressWarnings("unchecked")
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
			for (GrantedAuthority authority : authorities) {
				// Either Admin or the users with EDIT role are allowed to go ahead.
				if (authority.getAuthority().equals("admin") || authority.getAuthority().equals("ROLE_ALLOW_EDIT")) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
