package com.example.demo.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.AUTH.UserDetailsImpl;
import com.example.demo.Dati.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
    UserRepository userRep;
	
	public UserDetailsServiceImpl(UserRepository userRep) {
		this.userRep = userRep;
	}

	@Override
	public UserDetails loadUserByUsername(String stringAccessCode) {
		// TODO Auto-generated method stub
		User user = userRep.findByStringAccessCode(stringAccessCode).orElseThrow();
		
		return new UserDetailsImpl(user);
	}

}
