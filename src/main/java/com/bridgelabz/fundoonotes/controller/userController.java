package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

 

public class userController {
	/*
	 * @Autowired private UserService userservice;
	 * 
	 * @Autowired private User user;
	 * 
	 * @GetMapping("/getalll") public ResponseEntity<UserResponse> get() throws
	 * Exception { userservice.get(); return
	 * ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(user, 200,
	 * "verified")); }
	 */
}
