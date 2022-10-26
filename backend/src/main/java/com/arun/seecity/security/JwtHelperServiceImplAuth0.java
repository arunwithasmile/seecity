/**
 * 
 */
package com.arun.seecity.security;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * @author arun
 *
 */
@Service
@Primary
public class JwtHelperServiceImplAuth0 implements JwtHelperService {

	@Autowired
	private UserDetailsService userDetailsService;
	
	private Algorithm algorithm = Algorithm.HMAC256(secret);

	public String generateToken(UserDetails userDetails, List<String> roles) {
		String userName = userDetails.getUsername();
		Date now = new Date();
		String token = JWT.create().withIssuer("auth0").withSubject(userName).withClaim("roles", roles).withIssuedAt(now)
				.withExpiresAt(new Date(now.getTime() + EXPIRE_TIME_HRS * 60 * 60 * 1000)).sign(algorithm);
		return token;
	}

	public boolean isValid(String token) {
		if (token == null || token.isEmpty()) {
			return false;
		}
		try {
			String userName = verify(token);
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			return userDetails != null;
		} catch (ExpiredJwtException e) {
			return false;
		}
	}

	private String verify(String token) {
		JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
		DecodedJWT jwt = verifier.verify(token);
		String userName = jwt.getSubject();
		return userName;
	}

	public Authentication getAuthentication(String token) {
		String userName = verify(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}
}
