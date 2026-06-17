package com.example.demo.AUTH;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Entity.User;

import jakarta.annotation.Nullable;


public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = -6794177655898003884L;
	
	private final User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}

	//questo metodo controlla che il ruolo sia admin
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    return //Collections.emptyList();
	    		
	    		user.getRole().stream()
	               .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().toUpperCase()))
	               .collect(Collectors.toSet());
	}

	
	@Override
	public @Nullable String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
	    return user.getStringAccessCode(); 
	}

	
	@Override 
	public boolean isAccountNonExpired() { return true; }
	@Override 
	public boolean isAccountNonLocked() { return true; }
	@Override 
	public boolean isCredentialsNonExpired() { return true; }
	@Override 
	public boolean isEnabled() { return true; }
	
	

}
