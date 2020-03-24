package com.bridgelabz.fundoonotes.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.SearchNote;
import com.bridgelabz.fundoonotes.DTO.UpdateNote;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

@RestController
public class NoteController {

	@Autowired
	private NoteService noteservice;
	@Autowired
	private Note note;

	@PostMapping("/createnote/{token}")
	public ResponseEntity<UserResponse> AddNote(@RequestHeader("token") String token, @RequestBody NoteDto notedto) {
		Note note = noteservice.createNote(token, notedto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "New note is created"));

	}

	@GetMapping("/getall")
	public ResponseEntity<UserResponse> getallNotes() {
		List<Note> note = noteservice.getAllNotes();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "current notes list"));
	}

	@GetMapping("/get/{note_id}")
	public ResponseEntity<UserResponse> get(@PathVariable Long note_id) {
		Note note = noteservice.getNoteById(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "view note"));
	}

	@PutMapping("/updateNote/{note_id}")
	public ResponseEntity<UserResponse> updateNote(@PathVariable Long note_id, @RequestBody UpdateNote updatenote) {
		Note note = noteservice.updatenote(note_id, updatenote);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "note list is updated"));
	}

	@PutMapping("/archive/{note_id}")
	public ResponseEntity<UserResponse> IsArchive(@PathVariable Long note_id) {
		Note note = noteservice.Archive(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "note archived"));
	}

	@PutMapping("/pin/{note_id}")
	public ResponseEntity<UserResponse> Ispinned(@PathVariable Long note_id) {
		Note note = noteservice.Pinned(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "note pinned"));
	} 

	@DeleteMapping("/delete/{note_id}")
	public ResponseEntity<UserResponse> delete(@PathVariable Long note_id) {
		noteservice.deleteNote(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(200, "Note is Deleted"));
	}

	@PostMapping("/EditReminder/{note_id}")
	public ResponseEntity<UserResponse> EditRem(@PathVariable Long note_id, @RequestBody LocalDateTime remind) {
		Note note = noteservice.remindMe(note_id, remind);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "note pinned"));
	}

	@DeleteMapping("deletereminder/{note_id}")
	public ResponseEntity<UserResponse> DeleteRem(@PathVariable Long note_id) {
		noteservice.deleteRem(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(200, "Reminder is Deleted"));

	}
	 
	
	@GetMapping("/GetAllNotesByTitleAndDescription")
	public ResponseEntity<UserResponse> getNotesBYTitleAndDescription(@RequestBody  SearchNote searchnote) {
		 List<Note> note = noteservice.getNotesByTitleAndDescription(searchnote);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(note, 200, "notes by title by description"));
	}

}
