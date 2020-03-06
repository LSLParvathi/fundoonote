package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@PostMapping("/createnote/{token}")
	public ResponseEntity<UserResponse> AddNote(@PathVariable("token") String token, @RequestBody NoteDto notedto) {

		Note n = noteservice.createNote(token, notedto);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(n, 200, "note is added"));

	}

	@GetMapping("/readall")
	public ResponseEntity<UserResponse> getallNotes() throws Exception {
		try {
			List<Note> note = noteservice.getAllNotes();
			if (note != null) {
				return ResponseEntity.status(HttpStatus.ACCEPTED)
						.body(new UserResponse(note, 200, "notes can be viewd"));
			}
		} catch (Exception e) {
			System.out.println("there is some error-----");
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new UserResponse(401, "note can not be viewed"));

	}

	@GetMapping("/get/{note_id}")
	public ResponseEntity<UserResponse> get(@PathVariable Long note_id) {
		try {
			Note note = noteservice.get(note_id);
			if (note != null) {
				return ResponseEntity.status(HttpStatus.ACCEPTED)
						.body(new UserResponse(note, 200, "note can be viewed"));
			}
		} catch (Exception e) {
			System.out.println("data with this id does not exist");
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new UserResponse(401, "note can't be viewed"));

	}

	@DeleteMapping("/delete/{note_id}")
	public ResponseEntity<UserResponse> delete(@PathVariable Long note_id) {
		return null;

	}

}
