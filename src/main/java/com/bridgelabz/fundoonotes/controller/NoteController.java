package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utilis.JWToperations;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

@RestController
public class NoteController {

	@Autowired
	private NoteService noteservice;
	 
	 
	@PostMapping("/write/{token}")
	public ResponseEntity<UserResponse> AddNote(@PathVariable("token") String token,@RequestBody NoteDto notedto) throws Exception {
		System.out.println("---token is: "+token);
		try {
		Note n =noteservice.createNote(token,notedto);
		System.out.println("value is: "+n);
		 if(n != null)
		 {
			 return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(n, 200, "note is added")); 
		 } 
		}
		catch (Exception e) {
			 System.out.println("there is some error-----");
		}
		 return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new UserResponse(401, "note is not added"));
	}
	
	/*
	 * @GetMapping("/readall") public List<Note> getAllNotes() { return
	 * noteservice.getAllNotes();
	 * 
	 * }
	 */
	
 
	@GetMapping("/readall")
	public ResponseEntity<UserResponse> 
	 
}
