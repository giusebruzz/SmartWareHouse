package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AUTH.UserDetailsImpl;
import com.example.demo.DTO.ToolDTO;
import com.example.demo.Exception.ToolAlreadyExistException;
import com.example.demo.Exception.ToolNotFoundException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Service.ToolService;

@RestController
@Validated
@RequestMapping("/tool")
public class ToolController {
	
	ToolService toolSer;

	public ToolController(ToolService toolSer) {
		super();
		this.toolSer = toolSer;
	}
	
	@PostMapping("/createTool")
	public ResponseEntity<?>createTool(@RequestBody ToolDTO createTool) throws ToolAlreadyExistException {
		toolSer.createTool(createTool);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/modTool")
	public ResponseEntity<?>modStateTool(@RequestBody ToolDTO createTool) throws ToolNotFoundException {
		toolSer.changeStateTool(createTool);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/takeTheTool/{nameTool}")
    public ResponseEntity<?>takeTheTool(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String nameTool) 
    		throws ToolNotFoundException, UserNotFoundException{
		
		return ResponseEntity.ok(toolSer.takeTheTool(nameTool, userDetails.getUsername()).getId());
    	
    }
	
	@PostMapping("/putTheToolAway")
	public ResponseEntity<?>putTheToolAway(@RequestBody ToolDTO toolDTO,@AuthenticationPrincipal UserDetailsImpl userDetails) 
			throws ToolNotFoundException, UserNotFoundException{
		
		return ResponseEntity.ok(toolSer.putTheToolAway(toolDTO, userDetails.getUsername()).getId());
				
	}
	
	@DeleteMapping("/deleteTool/{nameTool}")
	public ResponseEntity<Void>deleteTool(@PathVariable String nameTool) throws ToolNotFoundException{
		
		toolSer.deleteTool(nameTool);
		
		return ResponseEntity.ok().build();
	}
	
	
}
