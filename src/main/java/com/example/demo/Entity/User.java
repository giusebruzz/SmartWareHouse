package com.example.demo.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.ENUM.StatusUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "the userName can't be empty")
	@Column(name = "username", nullable = false, unique = true)
	private String userName;
	@NotBlank(message = "the AccessCode can't be empty")
	@Column(name = "stringsccesssode", nullable = false, unique = true)
	private String stringAccessCode;
	@NotBlank(message = "the password can't be empty")
	@Size(min = 8, message = "the password has to be atleast 8 cheracheter")
	@Column(name = "password", nullable = false, unique = true)
	private String password;
	
	private LocalDate createAt;
	
	@Enumerated(EnumType.STRING)
	private StatusUser statusUser;
	
	@OneToMany(mappedBy = "user")
	private List<LastSeen> lastSeen = new ArrayList<>();
	@OneToMany(mappedBy = "tool_user")
	private List<Tool>tools = new ArrayList<>();
	@ManyToOne
	@JsonIgnore
	private Work user_work;
	
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	    name = "user_role",
	    joinColumns = @JoinColumn(name = "user_id"),      // riferimento alla tabella ApkInfo
	    inverseJoinColumns = @JoinColumn(name = "role_id") // riferimento alla tabella Category
	)
	private Set<Role> role = new HashSet<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}

	public List<LastSeen> getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(List<LastSeen> lastSeen) {
		this.lastSeen = lastSeen;
	}

	public StatusUser getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(StatusUser statusUser) {
		this.statusUser = statusUser;
	}

	public List<Tool> getTools() {
		return tools;
	}

	public void setTools(List<Tool> tools) {
		this.tools = tools;
	}

	public Work getUser_work() {
		return user_work;
	}

	public void setUser_work(Work user_work) {
		this.user_work = user_work;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}
}
