package com.bridgelabz.fundoonotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.DTO.Updatepassword;
import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.DTO.updateInformation;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.AmazonS3Service;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utilis.JMSoperations;
import com.bridgelabz.fundoonotes.utilis.JWToperations;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice;
	@Autowired
	private JMSoperations ope1;
	@Autowired
	private UserRepository userrepository;
	User user = new User();
	@Autowired
	private JWToperations ope;
	@Autowired
	private AmazonS3Service amazonS3Service;

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
		String user = userservice.userlogin(userinformation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(user, 200, "successfully logged in"));

	}

	@GetMapping("/getall")
	public ResponseEntity<UserResponse> getallusers() {
		List<User> user = userservice.getall();
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

	@PostMapping("/addprofilepic")
	public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
		this.amazonS3Service.uploadFileToS3Bucket(file, true);

		Map<String, String> response = new HashMap<>();
		response.put("message", "file [" + file.getOriginalFilename() + "] uploading request submitted successfully.");

		return response;
	}

	@DeleteMapping("/deleteprofilepic")
	public Map<String, String> deleteFile(@RequestParam("file_name") String fileName) {
		this.amazonS3Service.deleteFileFromS3Bucket(fileName);

		Map<String, String> response = new HashMap<>();
		response.put("message", "file [" + fileName + "] removing request submitted successfully.");

		return response;
	}

	@GetMapping("/check")
	public String send() {
		return "hai";
	}

}
