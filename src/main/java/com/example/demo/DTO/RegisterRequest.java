package com.example.demo.DTO;

import java.util.HashSet;
import java.util.Set;

public class RegisterRequest {
	
	private String userName;
	private String stringAccessCode;
	private String password;
	
	private Set<String> role = new HashSet<>();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStringAccessCode() {
		return stringAccessCode;
	}
	public void setStringAccessCode(String stringAccessCode) {
		this.stringAccessCode = stringAccessCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}

}
