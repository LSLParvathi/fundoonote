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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
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


	@PostMapping("/createlable/{token}")
	public ResponseEntity<Response> createLable(@PathVariable("token") String token, @RequestBody NoteDto notedto)
			throws LableException {
		Lable lable = lableservice.createLable(notedto, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response(lable, 201,env.getProperty("newlable")));
	}

	@PostMapping("/AddlableTonNote/{token}/{note_id}/{lable_id}")
	public ResponseEntity<Response> AddLableToNote(@PathVariable("token") String token, @PathVariable Long note_id,
			@PathVariable Long lable_id) throws NoteExceptions, LableException {
		Note note = lableservice.AddLableToNote(token, note_id, lable_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response(note, 200,env.getProperty("addlable")));
	}

	@GetMapping("/getlables")
	public ResponseEntity<Response> GetAllLables() {
		List<Lable> lable = lableservice.getallLables();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(lable, 200, env.getProperty("list")));

	}

	@PutMapping("/updateLable/{id}")
	public ResponseEntity<Response> Updatelable(@PathVariable Long id, @RequestBody UpdateLable updatelable)
			throws LableException {
		Lable lable = lableservice.updateLables(updatelable, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(lable, 200,env.getProperty("update")));

	}

	@DeleteMapping("/deletelable/{id}")
	public ResponseEntity<Response> deletelable(@PathVariable Long id) throws LableException {
		lableservice.deleteLables(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(204,env.getProperty("deletelable")));

	}

	@DeleteMapping("/deletelablefromNote/{note_id}/{lable_id}")
	public ResponseEntity<Response> deleteLablefromNote(@PathVariable Long note_id, @PathVariable Long lable_id)
			throws NoteExceptions, LableException {
		lableservice.deleteLablesFromNote(note_id, lable_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(204,env.getProperty("deletelable")));

	}
}
