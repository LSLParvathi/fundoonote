package com.bridgelabz.fundoonotes.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.service.LableService;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

@RestController
public class LabelController {
	@Autowired
	private LableService lableservice; 

	@PostMapping("/createlable/{token}")
	public ResponseEntity<UserResponse> createLable(@PathVariable("token") String token,
			@RequestBody  NoteDto notedto) { 
		Lable lable = lableservice.createLable(notedto, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new UserResponse(lable, 200, "new lable is created successfully"));
	}
	
	@PostMapping("/AddlableTonNote/{token}/{note_id}/{lable_id}")
	public ResponseEntity<UserResponse> AddLableToNote(@PathVariable("token") String token ,@PathVariable Long note_id,@PathVariable Long lable_id) { 
		 Note note = lableservice.AddLableToNote( token,note_id,lable_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new UserResponse( note, 200, "new lable is created successfully"));
	}

	@GetMapping("/getlables")
	public ResponseEntity<UserResponse> GetAllLables() {
		List<Lable> lable = lableservice.getallLables();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(lable, 200, "current lables list"));

	}

	@PutMapping("/updateLable/{id}")
	public ResponseEntity<UserResponse> Updatelable(@PathVariable Long id, @RequestBody UpdateLable updatelable) {
		Lable lable = lableservice.updateLables(updatelable, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(lable, 200, "lable is updated"));

	}

	@DeleteMapping("/deletelable/{id}")
	public ResponseEntity<UserResponse> deletelable(@PathVariable Long id) {
		lableservice.deleteLables(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(200, "lable is deleted"));

	}
	
	@DeleteMapping("/deletelablefromNote/{note_id}/{lable_id}")
	public ResponseEntity<UserResponse> deleteLablefromNote(@PathVariable Long note_id,@PathVariable Long lable_id) {
		lableservice.deleteLablesFromNote(note_id,lable_id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(200, "lable is deleted"));

	}
}
