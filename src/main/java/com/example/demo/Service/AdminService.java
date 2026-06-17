package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.LastSeenDTO;
import com.example.demo.Entity.LastSeen;
import com.example.demo.Entity.User;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.LastSeenRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.ToolRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.WorkRepository;

@Service
public class AdminService {
	
	UserRepository userRep;
	ToolRepository toolRep;
	WorkRepository workRep;
	RoleRepository roleRep;
	LastSeenRepository lastSeenRep;
	CheckClassForMethods checkMethods;

	public AdminService(UserRepository userRep, ToolRepository toolRep, WorkRepository workRep,
			CheckClassForMethods checkMethods,RoleRepository roleRep,LastSeenRepository lastSeenRep) {
		super();
		this.userRep = userRep;
		this.toolRep = toolRep;
		this.workRep = workRep;
		this.checkMethods = checkMethods;
		this.roleRep = roleRep;
		this.lastSeenRep = lastSeenRep;
	}

	public LastSeenDTO getAllUserLastSeen(String stringAccessCode) throws UserNotFoundException  {
		 
		 User user = checkMethods.checkIfUserIsPresent(stringAccessCode);
		
		 List<LocalDateTime> lastSeenList = user.getLastSeen().stream().map(LastSeen::getSeenForTheLastTime).toList();
		 
		 
		 LastSeenDTO dto = new LastSeenDTO();
		 dto.setLastSennUser(lastSeenList);

		 return dto;
		
	}
	
	public void deleteUser(String stringAccessCode) throws UserNotFoundException {
		
		User user = checkMethods.checkIfUserIsPresent(stringAccessCode);
		
		toolRep.deleteAll(user.getTools());
		
		lastSeenRep.deleteAll(user.getLastSeen());
		
		userRep.delete(user);
		
	}
	

}
