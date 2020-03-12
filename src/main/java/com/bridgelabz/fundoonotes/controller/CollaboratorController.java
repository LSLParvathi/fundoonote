package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.service.CollaboratorService;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

@RestController
public class CollaboratorController {

	@Autowired
	private CollaboratorService collaboratorservice; 

	@PostMapping("/addCollaborator/{id}")
	public ResponseEntity<UserResponse> AddCollaborator(@RequestHeader("token") String token,
			@RequestParam("email") String email, @PathVariable Long id) {
		Note note = collaboratorservice.AddCol(email, id, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "user is collaborator"));

	}

	@DeleteMapping("/deletecollaborator/{id}")
	public ResponseEntity<UserResponse> DelCollaborator(@RequestHeader("token") String token,
			@RequestParam("email") String email, @PathVariable Long id) {
		collaboratorservice.delCollaborator(email, token, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(200, "collaborated user is deleted"));

	}

	@GetMapping("/getallCollaborators/{id}")
	public ResponseEntity<UserResponse> GetAllCollaborators(@PathVariable Long id,@RequestHeader("token") String token) {
		List<User> coluser = collaboratorservice.getAllColl(token ,id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(coluser, 200, "user is collaborator"));

	}

}
