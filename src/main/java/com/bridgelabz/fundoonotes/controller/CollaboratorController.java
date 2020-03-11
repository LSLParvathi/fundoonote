package com.bridgelabz.fundoonotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.DTO.CollaboratorDto;
import com.bridgelabz.fundoonotes.model.Collaborator;
import com.bridgelabz.fundoonotes.service.CollaboratorService;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

@RestController
public class CollaboratorController {
	@Autowired
	private CollaboratorService colservice;

	@PostMapping("/AddCollaborator/{id}")
	public ResponseEntity<UserResponse> AddCollaborator(@PathVariable Long id, @RequestBody CollaboratorDto collaboratordto) {
		Collaborator collaborator = colservice.addcol(id, collaboratordto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(collaborator, 200, "user is collaborated"));
	}

}
