package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.CreateWorkDTO;
import com.example.demo.DTO.WorkDTO;
import com.example.demo.ENUM.StateWork;
import com.example.demo.ENUM.StatusUser;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Work;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Exception.WorkAlreadyExistException;
import com.example.demo.Exception.WorkNotFoundException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.WorkRepository;

@Service
public class WorkService {
	
	WorkRepository workRep;
	UserRepository userRep;
	CheckClassForMethods checkMethods;

	public WorkService(WorkRepository workRep, UserRepository userRep,CheckClassForMethods ceckMethods) {
		super();
		this.workRep = workRep;
		this.userRep = userRep;
		this.checkMethods = ceckMethods;
	}
	
	public Work createWork(CreateWorkDTO createWorkDTO) throws WorkAlreadyExistException {
		
		if(workRep.existsByName(createWorkDTO.getName())) {
			throw new WorkAlreadyExistException("work already exists");
		}
		
		Work newWork = new Work();
		
		newWork.setName(createWorkDTO.getName());
		newWork.setStateWork(createWorkDTO.getStateWork());
		newWork.setStateWork(StateWork.IN_PROGRESS);
		
		return workRep.save(newWork);
		
	}
	
    public Work takeTheJob(String nameJob,String stringAccessCode) throws WorkNotFoundException, UserNotFoundException {
		
		Work takeTheJob = checkMethods.checkIfWorkIsPresent(nameJob);
		
		User takeTheJobWithUser = checkMethods.checkIfUserIsPresent(stringAccessCode);
				
		List<User> users = new ArrayList<>();
		
		users.add(takeTheJobWithUser);
		
		takeTheJob.setUsers(users);
		
		takeTheJobWithUser.setUser_work(takeTheJob);
		
		takeTheJobWithUser.setStatusUser(StatusUser.WORKING);
		
		return workRep.save(takeTheJob);
		
	}
    
    public List<String> getAllTheUserThatAreWorkingOnThisJob(String nameJob) throws WorkNotFoundException {
    	
    	Work findWork = checkMethods.checkIfWorkIsPresent(nameJob);
    	
    	List<String> userNames = new ArrayList<>();
    	List<User> userFound = new ArrayList<>();
    	
    	for(User w: findWork.getUsers()) {
    		userFound.add(w);
    	}
    	
    	userNames = userFound.stream().map(l -> l.getUserName()).toList();
    	
    	return userNames;
    	
    }
    
    public Work updateJob(WorkDTO workDTO) throws WorkNotFoundException {
    	
    	Work updateWork = checkMethods.checkIfWorkIsPresent(workDTO.getName());
    	
    	updateWork.setProblem(workDTO.getProblem());
    	updateWork.setStateWork(workDTO.getStateWork());
    	
    	return workRep.save(updateWork);
    	
    }
    
    public void closedAJob(String nameJob) throws WorkNotFoundException {
    	
    	Work closedWork = checkMethods.checkIfWorkIsPresent(nameJob);
    	
    	closedWork.setStateWork(StateWork.COMPLETED);
    	
    	for(User u: closedWork.getUsers()) {
    		u.setStatusUser(StatusUser.FREE);
    		userRep.save(u);
    	}
    	
    	workRep.save(closedWork);
    	
    	
    }
    
   

}
