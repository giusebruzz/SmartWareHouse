package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AUTH.AuthResponse;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.Exception.UserAlreadyExistException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Service.AuthService;


@RestController
@Validated
@RequestMapping("/auth")
public class AuthController {
	
	AuthService userSer;

	public AuthController(AuthService userSer) {
		super();
		this.userSer = userSer;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody RegisterRequest registerReq) throws UserAlreadyExistException {
		
		userSer.registerUser(registerReq);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
				
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest loginRep) throws UserNotFoundException{
	
		return ResponseEntity.ok(userSer.loginUser(loginRep));
			
		
	}

}
