package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String>handleUserAlreadyExist(UserAlreadyExistException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(ToolAlreadyExistException.class)
	public ResponseEntity<String>handlerToolAlreadyExist(ToolAlreadyExistException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(ToolNotFoundException.class)
	public ResponseEntity<String>handlerToolNotFound(ToolNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String>handlerUserNotFound(UserNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(WorkAlreadyExistException.class)
	public ResponseEntity<String>handlerWorkAlreadyExist(WorkAlreadyExistException e){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	}
	
	@ExceptionHandler(WorkNotFoundException.class)
	public ResponseEntity<String>handlerWorkNotFound(WorkNotFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String>handleException(Exception e){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
	

}
