package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.Response.Response;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.service.CollaboratorService;

@RestController
public class CollaboratorController {

	@Autowired
	private CollaboratorService collaboratorservice;
	@Autowired
	private Environment env;


	@PostMapping("/addCollaborator/{id}")
	public ResponseEntity<Response> AddCollaborator(@RequestHeader("token") String token,
			@RequestParam("email") String email, @PathVariable Long id,BindingResult result) throws NoteExceptions {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));} 
		Note note = collaboratorservice.AddCol(email, id, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 201,env.getProperty("coluser") ));

	}

	@DeleteMapping("/deletecollaborator/{id}")
	public ResponseEntity<Response> DelCollaborator(@RequestHeader("token") String token,
			@RequestParam("email") String email, @PathVariable Long id,BindingResult result) throws NoteExceptions {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));}
	
		collaboratorservice.delCollaborator(email, token, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response(204,env.getProperty("delcoluser")));

	}

	@GetMapping("/getallCollaborators/{id}")
	public ResponseEntity<Response> GetAllCollaborators(@PathVariable Long id,
			@RequestHeader("token") String token) throws NoteExceptions {
		List<User> coluser = collaboratorservice.getAllColl(token, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response(coluser, 200, env.getProperty("list")));

	}

}
