package com.example.demo.Exception;

public class WorkAlreadyExistException extends Exception{

	private static final long serialVersionUID = 7990843821758194384L;

	public WorkAlreadyExistException(String msg) {
		super(msg);
	}
	
}
