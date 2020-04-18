package com.bridgelabz.fundoonotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.bridgelabz.fundoonotes.DTO.LableDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.Exceptions.LableException;
import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.Response.Response;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.service.LableService;

@RestController
public class LabelController {
	@Autowired
	private LableService lableservice;
	@Autowired
	private Environment env;

	@PostMapping("/createlable")
	public ResponseEntity<Response> addLable(@RequestHeader("token") String token, @RequestBody LableDto labledto)
			throws LableException {
		Lable lable = lableservice.createLable(labledto, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(lable, 201, env.getProperty("newlable")));
	}

	@PostMapping("/AddlableTonNote/{note_id}/{lable_id}")
	public ResponseEntity<Response> AddLableToNote(@RequestHeader("token") String token, @PathVariable Long note_id,
			@PathVariable Long lable_id) throws NoteExceptions, LableException {
		Note note = lableservice.AddLableToNote(token, note_id, lable_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(note, 200, env.getProperty("addlable")));
	}

	@GetMapping("/getlables")
	public ResponseEntity<Response> GetAllLables() {
		List<Lable> lable = lableservice.getallLables();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(lable, 200, env.getProperty("list")));

	}

	 

	@DeleteMapping("/deletelablefromNote/{note_id}/{lable_id}")
	public ResponseEntity<Response> deleteLablefromNote(@PathVariable Long note_id, @PathVariable Long lable_id,
			@RequestHeader("token") String token) throws NoteExceptions, LableException {
		lableservice.deleteLablesFromNote(note_id, lable_id, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(204, env.getProperty("deletelable")));

	}

	@DeleteMapping("/deletelable/{id}")
	public ResponseEntity<Response> deletelable(@PathVariable Long id, @RequestHeader("token") String token)
			throws LableException {
		lableservice.deleteLables(id, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(204, env.getProperty("deletelable")));

	}
}
