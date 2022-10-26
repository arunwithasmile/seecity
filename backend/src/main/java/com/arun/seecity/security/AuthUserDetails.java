/**
 * 
 */
package com.arun.seecity.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arun.seecity.model.User;

/**
 * @author arun
 *
 */
public class AuthUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4216348636691139417L;

	private User user;

	private List<GrantedAuthority> roles;

	public AuthUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (roles != null) {
			return this.roles;
		}
		return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return user.getPasswordX();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<String> getRoles() {
		return user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
	}
}
