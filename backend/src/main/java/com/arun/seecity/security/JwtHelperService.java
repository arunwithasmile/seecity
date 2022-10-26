/**
 * 
 */
package com.arun.seecity.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author arun
 *
 */
@Service
public class JwtHelperService {

	private static final String secret = "(ityS3cret";
	private static final int EXPIRE_TIME_HRS = 0;

	@Autowired
	private UserDetailsService userDetailsService;

	private Claims getClaims(String jwt) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwt).getBody();
	}

	public String generateToken(UserDetails userDetails, List<String> roles) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", claims);
		String userName = userDetails.getUsername();
		JwtBuilder builder = Jwts.builder();
		builder = builder.setClaims(claims);
		builder = builder.setSubject(userName);
		builder = builder.setIssuedAt(new Date());
		builder = builder.signWith(SignatureAlgorithm.HS256, secret);
		String token = builder.compact();
		return token;
	}

	public boolean isValid(String jwt) {
		if (jwt == null || jwt.isEmpty()) {
			return false;
		}
		try {
			Claims claims = getClaims(jwt);
			String userName = (String) claims.get(Claims.SUBJECT);
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			return userDetails != null;
		} catch (ExpiredJwtException e) {
			return false;
		}
	}

	public String resolveToken(HttpServletRequest request) {
		final String autorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (autorizationHeader != null && autorizationHeader.startsWith("Bearer ")) {
			return autorizationHeader.substring(7);
		}
		return null;
	}

	public Authentication getAuthentication(String jwt) {
		Claims claims = getClaims(jwt);
		String userName = (String) claims.get(Claims.SUBJECT);
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
}
