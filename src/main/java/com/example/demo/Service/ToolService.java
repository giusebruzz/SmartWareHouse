package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.ToolDTO;
import com.example.demo.Dati.Tool;
import com.example.demo.Dati.User;
import com.example.demo.ENUM.StateTool;
import com.example.demo.Exception.ToolAlreadyExistException;
import com.example.demo.Exception.ToolNotFoundException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.ToolRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class ToolService {
	
	ToolRepository toolRep;
	UserRepository userRep;
	CheckClassForMethods checkMethods;
	
	public ToolService(ToolRepository toolRep,UserRepository userRep, CheckClassForMethods checkMethods) {
		super();
		this.toolRep = toolRep;
		this.userRep = userRep;
		this.checkMethods = checkMethods;
	}

	public Tool createTool(ToolDTO toolDTO) throws ToolAlreadyExistException  {
		
		if(toolRep.existsByName(toolDTO.getName())){
			throw new ToolAlreadyExistException("already existing");
		}
		
		Tool newTool = new Tool();
		
		newTool.setName(toolDTO.getName());
		newTool.setStateTool(toolDTO.getStateTool());
		newTool.setCreateAt(java.time.LocalDate.now());
		
		return toolRep.save(newTool);
		
	}
	
	public Tool changeStateTool(ToolDTO toolDTO) throws ToolNotFoundException {
		
		Tool newTool = checkMethods.checkIfToolIsPresent(toolDTO.getName());
		
		newTool.setStateTool(toolDTO.getStateTool());
		newTool.setUpdateAt(java.time.LocalDate.now());
		
		return toolRep.save(newTool);
		
		
	}
	
	public Tool takeTheTool(String nameTool,String stringaccesscode) throws ToolNotFoundException, UserNotFoundException {
		
		Tool takeTool = checkMethods.checkIfToolIsPresent(nameTool);
		
		User userFound = checkMethods.checkIfUserIsPresent(stringaccesscode);
		
		if(takeTool.getStateTool().equals(StateTool.BROKEN) || takeTool.getStateTool().equals(StateTool.IN_USE)) {
			
			throw new UserNotFoundException("you can't take "+takeTool.getName()+" it's broken or in use");
			
		}
		
		takeTool.setStateTool(StateTool.IN_USE);
		
		takeTool.setTool_user(userFound);
		
		takeTool.setWork(userFound.getUser_work());
		
		return toolRep.save(takeTool);
		
	} 
	
	public Tool putTheToolAway(ToolDTO toolDTO, String stringaccesscode) throws ToolNotFoundException, UserNotFoundException {
		
        Tool putItAway = checkMethods.checkIfToolIsPresent(toolDTO.getName());
		
		User userFound = checkMethods.checkIfUserIsPresent(stringaccesscode);
		
		if(!userFound.getTools().contains(putItAway)) {
			
			throw new ToolNotFoundException("tool not found");
		}
		
		putItAway.setStateTool(toolDTO.getStateTool());
		putItAway.setUpdateAt(java.time.LocalDate.now());
		
		putItAway.setWork(null);
		putItAway.setTool_user(null);
		
		return toolRep.save(putItAway);
		
	}
	
	public void deleteTool(String nameTool) throws ToolNotFoundException {
		
		 Tool deleteTool = checkMethods.checkIfToolIsPresent(nameTool);
		 
		 toolRep.delete(deleteTool);
		
	}

}
