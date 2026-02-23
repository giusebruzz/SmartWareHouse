package com.example.demo.DTO;


import com.example.demo.ENUM.StateWork;

public class WorkDTO {
	
	private String name;
	
	private String problem;
	
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

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	

}
