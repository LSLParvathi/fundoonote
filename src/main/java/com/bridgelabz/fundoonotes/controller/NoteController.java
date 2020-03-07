package com.bridgelabz.fundoonotes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.UpdateNote;
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
		Note note = noteservice.createNote(token, notedto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "New note is created"));

	}

	@GetMapping("/getall")
	public ResponseEntity<UserResponse> getallNotes() {
		Optional<List<Note>> note = noteservice.getAllNotes();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "current notes list"));
	}

	@GetMapping("/get/{note_id}")
	public ResponseEntity<UserResponse> get(@PathVariable Long note_id) {
		Note note = noteservice.getNoteById(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "view note"));
	}

	@DeleteMapping("/delete/{note_id}")
	public ResponseEntity<UserResponse> delete(@PathVariable Long note_id) {
		noteservice.deleteNote(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(200, "Note is Deleted"));
	}

	@PutMapping("/updateNote/{note_id}")
	public ResponseEntity<UserResponse> updateNote(@PathVariable Long note_id,@RequestBody UpdateNote updatenote) {
		Note note = noteservice.updatenote(note_id,updatenote);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "note list is updated"));
	}

}
