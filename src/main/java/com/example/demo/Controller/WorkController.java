package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AUTH.UserDetailsImpl;
import com.example.demo.DTO.CreateWorkDTO;
import com.example.demo.DTO.WorkDTO;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Exception.WorkAlreadyExistException;
import com.example.demo.Exception.WorkNotFoundException;
import com.example.demo.Service.WorkService;

@RestController
@Validated
@RequestMapping("/work")
public class WorkController {
	
	WorkService workSer;

	public WorkController(WorkService workSer) {
		super();
		this.workSer = workSer;
	}
	
	@PostMapping("/createWork")
	public ResponseEntity<?>createWork(@RequestBody CreateWorkDTO workDTO) throws WorkAlreadyExistException{
		
		workSer.createWork(workDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	
	@GetMapping("/takeTheJob/{nameJob}")
	public ResponseEntity<?>takeTheJob(@AuthenticationPrincipal UserDetailsImpl userDetails,
			@PathVariable String nameJob ) throws WorkNotFoundException, UserNotFoundException{
		
		return ResponseEntity.ok(workSer.takeTheJob(nameJob, userDetails.getUsername()).getId());
	
    }
	
	@GetMapping("/getAllUsersWorkingOnTheJob/{nameJob}")
	public ResponseEntity<?>getAllUsersWorkingOnTheJob(String nameJob) throws WorkNotFoundException{
		
	    return ResponseEntity.ok(workSer.getAllTheUserThatAreWorkingOnThisJob(nameJob));
		
	}
	
	@PostMapping("/updateJob")
	public ResponseEntity<?>updateJob(@RequestBody WorkDTO workDTO) throws WorkNotFoundException{
		
		workSer.updateJob(workDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping("/completeJob/{nameJob}")
	public ResponseEntity<Void>completeJob(@PathVariable String nameJob) throws WorkNotFoundException{
		
		workSer.closedAJob(nameJob);
		
		return ResponseEntity.noContent().build();
		
	}
	

}
