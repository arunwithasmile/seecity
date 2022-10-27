/**
 * 
 */
package com.arun.seecity.security;

import static com.arun.seecity.util.AppConstants.TOKEN_EXPIRE_TIME_HRS;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
//@Primary
public class JwtHelperServiceImpl implements JwtHelperService {

	@Autowired
	private UserDetailsService userDetailsService;

	private Claims getClaims(String jwt) {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwt).getBody();
	}

	public String generateToken(UserDetails userDetails, List<String> roles) {
		String userName = userDetails.getUsername();
		Date now = new Date();
		Claims claims = Jwts.claims().setSubject(userName);
		claims.put("roles", claims);
		JwtBuilder builder = Jwts.builder().setClaims(claims).setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + TOKEN_EXPIRE_TIME_HRS * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secret.getBytes()));
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

	public Authentication getAuthentication(String jwt) {
		Claims claims = getClaims(jwt);
		String userName = (String) claims.get(Claims.SUBJECT);
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		return new UsernamePasswordAuthenticationToken(userName, "", userDetails.getAuthorities());
	}
}
