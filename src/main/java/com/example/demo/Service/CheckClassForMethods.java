package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Tool;
import com.example.demo.Entity.User;
import com.example.demo.Entity.Work;
import com.example.demo.Exception.ToolNotFoundException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Exception.WorkNotFoundException;
import com.example.demo.Repository.ToolRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Repository.WorkRepository;

@Service
public class CheckClassForMethods {
	
	WorkRepository workRep;
	UserRepository userRep;
	ToolRepository toolRep;
	
	public CheckClassForMethods(WorkRepository workRep, UserRepository userRep, ToolRepository toolRep) {
		super();
		this.workRep = workRep;
		this.userRep = userRep;
		this.toolRep = toolRep;
	}
	
	
	public Work checkIfWorkIsPresent(String nameJob) throws WorkNotFoundException {
    	
    	Work workPresents = workRep.findByName(nameJob).orElseThrow(
    			() -> new WorkNotFoundException("work not found"));
    	
    	return workPresents;
    	
    }
    
    public User checkIfUserIsPresent(String stringAccessCode) throws UserNotFoundException {
    	
    	User userPresents = userRep.findByStringAccessCode(stringAccessCode).orElseThrow(
				() -> new UserNotFoundException("user not found"));
    	
    	return userPresents;
    	
    }
    
    
    
    public Tool checkIfToolIsPresent(String nameTool) throws ToolNotFoundException {
    	
    	Tool newTool = toolRep.findByName(nameTool).orElseThrow(
				() -> new ToolNotFoundException("tool not found"));
    	
    	return newTool;
    	
    }
	
	

}
