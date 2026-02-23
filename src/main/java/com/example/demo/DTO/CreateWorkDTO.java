package com.example.demo.DTO;

import com.example.demo.ENUM.StateWork;

public class CreateWorkDTO {
	
	private String name;
	
	private StateWork stateWork;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StateWork getStateWork() {
		return stateWork;
	}

	public void setStateWork(StateWork stateWork) {
		this.stateWork = stateWork;
	}
	
	

}
