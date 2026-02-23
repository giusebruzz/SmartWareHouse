package com.example.demo.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.AUTH.AuthResponse;
import com.example.demo.Configuration.SecurityConfig;
import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.RegisterRequest;
import com.example.demo.Dati.LastSeen;
import com.example.demo.Dati.Role;
import com.example.demo.Dati.User;
import com.example.demo.ENUM.StatusUser;
import com.example.demo.Exception.UserAlreadyExistException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repository.LastSeenRepository;
import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class AuthService {

    AuthenticationManager authenticationManager;
	UserRepository userRep;
	SecurityConfig securityConfig;
	JwtService jwtService;
	LastSeenRepository lastSeenRep;
	RoleRepository roleRep;
	CheckClassForMethods checkMethods;
	
    public AuthService(AuthenticationManager authenticationManager, UserRepository userRep,
			SecurityConfig securityConfig, JwtService jwtService, LastSeenRepository lastSeenRep,
			RoleRepository roleRep, CheckClassForMethods checkMethods) {
		super();
		this.authenticationManager = authenticationManager;
		this.userRep = userRep;
		this.securityConfig = securityConfig;
		this.jwtService = jwtService;
		this.lastSeenRep = lastSeenRep;
		this.roleRep = roleRep;
		this.checkMethods = checkMethods;
	}

	public User registerUser(RegisterRequest request) throws UserAlreadyExistException{
		
		if(userRep.existsByStringAccessCode(request.getStringAccessCode())) {
			throw new UserAlreadyExistException("user already exist");
		};
		
		User userCreated = new User();
	
		userCreated.setUserName(request.getUserName());
		userCreated.setStringAccessCode(request.getStringAccessCode());
		userCreated.setPassword(securityConfig.passwordEncoder().encode(request.getPassword()));
		userCreated.setCreateAt(java.time.LocalDate.now());
		userCreated.setStatusUser(StatusUser.FREE);
		
		setRoleUser(userCreated,request);
		
		return userRep.save(userCreated);
		
		
	}
	
	public void setRoleUser(User user, RegisterRequest request) {
		
		Set<Role>roles = new HashSet<>();
		
		for(String role: request.getRole()) {
			Role roleUser = roleRep.findByRole(role).orElseGet(() -> {
				Role newRole = new Role();
				newRole.setRole(role);
				return roleRep.save(newRole);
			});
			
			roles.add(roleUser);
		}
		
		user.setRole(roles);
		
	}
	
	public AuthResponse loginUser(LoginRequest login) throws UserNotFoundException  {
		
		 Authentication authentication = authenticationManager.authenticate( 
				 new UsernamePasswordAuthenticationToken(
						 login.getStringAccessCode(),
						 login.getPassword()
                         )
                 );
		 
		 modUserLastSeen(login);
		 
		 String jwt = jwtService.generateToken(login.getStringAccessCode());
		 AuthResponse auth = new AuthResponse(jwt);
		 
		 return auth;
		
	}
	
	public void modUserLastSeen(LoginRequest loginReq) throws UserNotFoundException  {
		
		User userSaveLastSeen = checkMethods.checkIfUserIsPresent(loginReq.getStringAccessCode());
		
		List<LastSeen> seenLogin = new ArrayList<>();
		 
		LastSeen lastSeenLogin = new LastSeen();
		lastSeenLogin.setSeenForTheLastTime(java.time.LocalDateTime.now());
		lastSeenLogin.setUser(userSaveLastSeen);
		
		seenLogin.add(lastSeenLogin);
		
		lastSeenRep.save(lastSeenLogin);
		
		userSaveLastSeen.setLastSeen(seenLogin);
		userRep.save(userSaveLastSeen);
		
		
	}
	


}
