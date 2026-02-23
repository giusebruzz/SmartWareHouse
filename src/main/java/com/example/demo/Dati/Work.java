package com.example.demo.Dati;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.ENUM.StateWork;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Work {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "the description can't be empty")
	@Column(name = "description", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "work")
	private List<Tool>tools = new ArrayList<>();
	@OneToMany(mappedBy = "user_work")
	private List<User>users = new ArrayList<>();
	
	private String problem;
	
	@Enumerated(EnumType.STRING)
	private StateWork stateWork;

	public Work() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tool> getTools() {
		return tools;
	}

	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}

	public StateWork getStateWork() {
		return stateWork;
	}

	public void setStateWork(StateWork stateWork) {
		this.stateWork = stateWork;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	
	
	
}
