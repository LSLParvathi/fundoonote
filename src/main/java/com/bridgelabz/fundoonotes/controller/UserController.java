package com.bridgelabz.fundoonotes.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.DTO.Updatepassword;
import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.Exceptions.UserExceptions;
import com.bridgelabz.fundoonotes.Response.Response;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.AmazonS3Service;
import com.bridgelabz.fundoonotes.service.UserService;

@PropertySource("classpath:message.properties")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice;
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private AmazonS3Service amazonS3Service;
	@Autowired
	private Environment env;

	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody UserDTO userdto, BindingResult result)
			throws IOException {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));
		}
		User user = userservice.register(userdto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 201, env.getProperty("register")));
	}

	@GetMapping("/verify")
	public ResponseEntity<Response> verifyUser(@RequestHeader("token") String token) throws Exception {
		User user = userservice.verifyUser(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 200, env.getProperty("verified")));
	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody UserInformation userinformation, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));
		}
		User user = userservice.userLogin(userinformation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 202, env.getProperty("login")));

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getUser(@PathVariable Long id) {
		User user = userrepository.getUserById(id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 200, env.getProperty("list")));
	}

	@PutMapping("/forgotpassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody Updatepassword updatepassword,
			@RequestHeader("token") String token, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));
		}
		User user = userservice.setNewPassword(updatepassword, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 200, env.getProperty("setpassword")));

	}

	@PostMapping("/addprofilepic")
	public ResponseEntity<Response> uploadFile(@RequestPart(value = "file") MultipartFile file,
			@RequestHeader("token") String token) {
		this.amazonS3Service.uploadFileToS3Bucket(file, true, token);
		Map<String, String> response = new HashMap<>();
		String filename = file.getOriginalFilename();
		response.put("message", "file [" + filename + "] uploading request submitted successfully.");
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response(response, 200, env.getProperty("uploadpic")));

	}

	@DeleteMapping("/deleteprofilepic")
	public ResponseEntity<Response> deleteFile(@RequestParam("file_name") String fileName,
			@RequestPart("token") String token) {
		this.amazonS3Service.deleteFileFromS3Bucket(fileName, token);
		Map<String, String> response = new HashMap<>();
		response.put("message", "file [" + fileName + "] removing request submitted successfully.");
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new Response(response, 200, env.getProperty("uploadpic")));
	}

}
