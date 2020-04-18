package com.bridgelabz.fundoonotes.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.UpdateNote;
import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.Response.Response;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.service.NoteService;
import com.bridgelabz.fundoonotes.utilis.RedisService;

@RestController
@PropertySource("classpath:message.properties")

public class NoteController {

	@Autowired
	private NoteService noteservice;
	@Autowired
	private RedisService redisservice;
	@Autowired
	private Environment env;

	@PostMapping("/createnote")
	public ResponseEntity<Response> createNote(@RequestHeader("token") String token, @RequestBody NoteDto notedto,
			BindingResult result) throws NoteExceptions {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));
		}
		Note note = noteservice.createNote(token, notedto);
		redisservice.putToken(token, note.getNote_id());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 201, env.getProperty("create")));

	}

	@PutMapping("/updateNote/{note_id}")
	public ResponseEntity<Response> updateNote(@PathVariable Long note_id, @RequestBody UpdateNote updatenote,
			@RequestHeader("token") String token) throws NoteExceptions {
		Note note = noteservice.updatenote(note_id, updatenote, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 200, env.getProperty("update")));
	}

	@GetMapping("/getall")
	public ResponseEntity<Response> getNotes(@RequestHeader("token") String token) throws NoteExceptions {
		List<Note> note = noteservice.getAllNotes(token);
		if (redisservice.getToken(token) == false)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(new Response(200, "No such token in the database"));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 200, env.getProperty("list")));
	}

	@GetMapping("/get/{note_id}")
	public ResponseEntity<Response> getNoteById(@PathVariable Long note_id, @RequestHeader("token") String token)
			throws NoteExceptions {
		Note note = new Note();
		note = noteservice.getNoteById(note_id, token);
		if (redisservice.getToken(token) == false)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body(new Response(200, "No such token in the database"));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 200, env.getProperty("list")));
	}

	@DeleteMapping("/delete/{note_id}")
	public ResponseEntity<Response> deleteNote(@PathVariable Long note_id, @RequestHeader("token") String token)
			throws NoteExceptions {
		noteservice.deleteNote(note_id, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(204, env.getProperty("deletenote")));
	}

	@PutMapping("/archive/{note_id}")
	public ResponseEntity<Response> archive(@PathVariable Long note_id, @RequestHeader("token") String token)
			throws NoteExceptions {
		Note note = noteservice.Archive(note_id, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response(note, 200, env.getProperty("notearchived")));
	}

	@PutMapping("/pin/{note_id}")
	public ResponseEntity<Response> pin(@PathVariable Long note_id, @RequestHeader("token") String token)
			throws NoteExceptions {
		Note note = noteservice.Pinned(note_id, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 200, env.getProperty("notepinned")));
	}

	@PostMapping("/EditReminder/{note_id}")
	public ResponseEntity<Response> EditRem(@PathVariable Long note_id, @RequestBody LocalDateTime remind,
			@RequestHeader("token") String token) throws NoteExceptions {
		Note note = noteservice.remindMe(note_id, remind, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 200, env.getProperty("notepinned")));
	}

	@DeleteMapping("deletereminder/{note_id}")
	public ResponseEntity<Response> DeleteRem(@PathVariable Long note_id, @RequestHeader("token") String token)
			throws NoteExceptions {
		noteservice.deleteRem(note_id, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(204, env.getProperty("deletereminder")));

	}

	/*
	 * @GetMapping("/GetAllNotesByTitleAndDescription/{text}") public
	 * ResponseEntity<Response> getNotesBYTitleAndDescription(@PathVariable String
	 * text,
	 * 
	 * @RequestHeader("token") String token) throws Exception { List<Note> note =
	 * noteservice.getNotesByTitleAndDescription(text); return
	 * ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 200,
	 * env.getProperty("list"))); }
	 */

	/*
	 * @GetMapping("/getall") public List<Note> getallNotes(@RequestHeader("token")
	 * String token,@QueryParam("note_id") Long note_id) throws NoteExceptions {
	 * return note_id !=null ? noteservice.getNoteByNote_id(note_id) :
	 * noteservice.getAllNotes();
	 * 
	 * }
	 */

}
