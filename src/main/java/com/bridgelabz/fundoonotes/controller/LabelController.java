package com.bridgelabz.fundoonotes.controller;

import javax.validation.Valid;

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

import com.bridgelabz.fundoonotes.DTO.LableDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.service.LableService;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

@RestController
public class LabelController {
	@Autowired
	private LableService lableservice; 

	@PostMapping("/createlable/{token}")
	public ResponseEntity<UserResponse> createLable(@PathVariable("token") String token,
			 @RequestBody LableDto labledto) {
		Lable lable = lableservice.createLable(labledto, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new UserResponse(lable, 200, "new lable is created successfully"));
	}

	@GetMapping("/getlables")
	public ResponseEntity<UserResponse> GetAllLables() {
		Lable lable = lableservice.getall();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(lable, 200, "current lables list"));

	}

	@PutMapping("/updateLable/{id}")
	public ResponseEntity<UserResponse> Updatelable(@PathVariable Long id, @RequestBody UpdateLable updatelable) {
		Lable lable = lableservice.updateLables(updatelable, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(lable, 200, "current lables list"));

	}

	@DeleteMapping("/deletelable/{id}")
	public ResponseEntity<UserResponse> deletelable(@PathVariable Long id) {
		lableservice.deleteLables(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(200, "current lables list"));

	}
}
