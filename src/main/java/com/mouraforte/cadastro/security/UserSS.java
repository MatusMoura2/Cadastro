package com.mouraforte.cadastro.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mouraforte.cadastro.domain.enums.Profiles;

public class UserSS implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserSS(Long id, String username, String password, Set<Profiles> profiles) {
		super();
		this.id = id;
		this.email = username;
		this.password = password;
		this.authorities = profiles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription()))
				.collect(Collectors.toSet());
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
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


}
