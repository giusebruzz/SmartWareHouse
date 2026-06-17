package com.example.demo.Entity;

import java.time.LocalDate;

import com.example.demo.ENUM.StateTool;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tool {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "the name can't be empty")
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	
	@ManyToOne
	@JsonIgnore
	private Work work;
	@ManyToOne
	@JsonIgnore
	private User tool_user;
	
	private LocalDate createAt;
	private LocalDate updateAt;
	
	@Enumerated(EnumType.STRING)
	private StateTool stateTool;

	public Tool() {
		super();
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

	public Work getWork() {
		return work;
	}

	public void setWork(Work work) {
		this.work = work;
	}

	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public LocalDate getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDate updateAt) {
		this.updateAt = updateAt;
	}

	public StateTool getStateTool() {
		return stateTool;
	}

	public void setStateTool(StateTool stateTool) {
		this.stateTool = stateTool;
	}

	public User getTool_user() {
		return tool_user;
	}

	public void setTool_user(User tool_user) {
		this.tool_user = tool_user;
	}

}
