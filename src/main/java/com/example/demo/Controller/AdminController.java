package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Service.AdminService;

@RestController
@Validated
@RequestMapping("/admin")
public class AdminController {
	
	AdminService adminSer;
	
	public AdminController(AdminService adminSer) {
		super();
		this.adminSer = adminSer;
	}
	
	@Transactional(readOnly = true)
	@PreAuthorize("hasRole('ADMIN') or #stringAccessCode == authentication.principal.username")
	@GetMapping("getLastSeen/{stringAccessCode}")
	public ResponseEntity<?> getLastSeen(@PathVariable String stringAccessCode)
			throws UserNotFoundException{
		
		return ResponseEntity.ok(adminSer.getAllUserLastSeen(stringAccessCode));
	}
	
	@Transactional
	@PreAuthorize("hasRole('ADMIN') or #stringAccessCode == authentication.principal.username")
	@DeleteMapping("/deleteUser/{stringAccessCode}")
	public ResponseEntity<Void> deleteUser(@PathVariable String stringAccessCode) throws UserNotFoundException{
		
		adminSer.deleteUser(stringAccessCode);
		
		return ResponseEntity.ok().build();
		
		
	}

}
