package com.bridgelabz.fundoonotes.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.Response.Genericresponse;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.utilis.RedisService; 

@RestController
public class NoteController {

	@Autowired
	private NoteService noteservice;
	Note note = new Note();
	@Autowired
	private RedisService redisservice;

	@PostMapping("/createnote")
	public ResponseEntity<Genericresponse> AddNote(@RequestHeader("token") String token, @RequestBody NoteDto notedto)
			throws NoteExceptions {
		Note note = noteservice.createNote(token, notedto);
		redisservice.putToken(token, note.getNote_id());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(note, 200, "New note is created"));

	}

	@GetMapping("/getall")
	public ResponseEntity<Genericresponse> getallNotes(@RequestHeader("token") String token) throws NoteExceptions {
		List<Note> note = noteservice.getAllNotes();
		if (redisservice.getToken(token) == false)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(new Genericresponse(200, "No such token in the database"));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(note, 200, "current notes list"));
	}

	@GetMapping("/get/{note_id}")
	public ResponseEntity<Genericresponse> get(@PathVariable Long note_id, @RequestHeader("token") String token) {
		Note note = new Note();
		try {
			note = noteservice.getNoteById(note_id);
		} catch (NoteExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (redisservice.getToken(token) == false)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(new Genericresponse(200, "No such token in the database"));

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(note, 200, "view note"));
	}

	@PutMapping("/updateNote/{note_id}")
	public ResponseEntity<Genericresponse> updateNote(@PathVariable Long note_id, @RequestBody UpdateNote updatenote,
			@RequestHeader("token") String token) throws NoteExceptions {
		Note note = noteservice.updatenote(note_id, updatenote);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(note, 200, "note list is updated"));
	}

	@PutMapping("/archive/{note_id}")
	public ResponseEntity<Genericresponse> IsArchive(@PathVariable Long note_id, @RequestHeader("token") String token) throws NoteExceptions {
		Note note = noteservice.Archive(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(note, 200, "note archived"));
	}

	@PutMapping("/pin/{note_id}")
	public ResponseEntity<Genericresponse> Ispinned(@PathVariable Long note_id, @RequestHeader("token") String token) throws NoteExceptions {
		Note note = noteservice.Pinned(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(note, 200, "note pinned"));
	}

	@DeleteMapping("/delete/{note_id}")
	public ResponseEntity<Genericresponse> delete(@PathVariable Long note_id, @RequestHeader("token") String token) throws NoteExceptions {
		noteservice.deleteNote(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(200, "Note is Deleted"));
	}

	@PostMapping("/EditReminder/{note_id}")
	public ResponseEntity<Genericresponse> EditRem(@PathVariable Long note_id, @RequestBody LocalDateTime remind,
			@RequestHeader("token") String token) throws NoteExceptions {
		Note note = noteservice.remindMe(note_id, remind);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(note, 200, "note pinned"));
	}

	@DeleteMapping("deletereminder/{note_id}")
	public ResponseEntity<Genericresponse> DeleteRem(@PathVariable Long note_id, @RequestHeader("token") String token) throws NoteExceptions {
		noteservice.deleteRem(note_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Genericresponse(200, "Reminder is Deleted"));

	}

	@GetMapping("/GetAllNotesByTitleAndDescription")
	public ResponseEntity<Genericresponse> getNotesBYTitleAndDescription(@RequestBody SearchNote searchnote,
			@RequestHeader("token") String token) throws NoteExceptions {
		List<Note> note = noteservice.getNotesByTitleAndDescription(searchnote);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Genericresponse(note, 200, "notes by title by description"));
	}

}
