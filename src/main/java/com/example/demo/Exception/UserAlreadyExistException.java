package com.example.demo.Exception;

public class UserAlreadyExistException extends Exception{

	private static final long serialVersionUID = -5774657271005178570L;
	
	public UserAlreadyExistException(String msg) {
		super(msg);
	}

}
