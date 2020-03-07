package com.bridgelabz.fundoonotes.controller;

import java.security.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.fundoonotes.DTO.Updatepassword;
import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.DTO.updateInformation;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.utilis.UserResponse;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utilis.JMSoperations;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice;
	@Autowired
	private JMSoperations ope1;
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private User user;
	@Autowired
	private JWToperations ope;

	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@Valid @RequestBody UserDTO userdto) {
		User user = userservice.register(userdto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(user, 200, "successfully registered"));
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<UserResponse> VerificationUser(@PathVariable("token") String token) throws Exception {
		User user = userservice.verifyUser(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(user, 200, "verified"));
	}

	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody UserInformation userinformation) {
		User user = userservice.userlogin(userinformation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(user, 200, "successfully logged in"));

	}

	@GetMapping("/getall")
	public ResponseEntity<UserResponse> getallusers() {
		User user = userservice.getall();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(user, 200, "current users list"));

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<UserResponse> get(@PathVariable Long id) {
		User userobj = userservice.getUserById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(userobj, 200, "user list"));
	}

	@PutMapping("/updateuser/{id}")
	public ResponseEntity<UserResponse> update(@RequestBody updateInformation updateinformation,
			@PathVariable Long id) {
		User user = userservice.updateuser(updateinformation, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(user, 200, "successfully updated"));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserResponse> delete(@PathVariable Long id) {
		userservice.deleteUser(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new UserResponse(200, "the user with id " + id + " has been deleted."));
	}

	@PutMapping("/forgotpassword")
	public ResponseEntity<UserResponse> setpassword(@RequestBody Updatepassword updatepassword) {
		User user = userservice.setnewpassword(updatepassword);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new UserResponse(user, 200, "new password has been set"));

	}
	
	 

}
