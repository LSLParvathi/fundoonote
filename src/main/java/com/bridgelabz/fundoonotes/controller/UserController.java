package com.bridgelabz.fundoonotes.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoonotes.DTO.Updatepassword;
import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.DTO.updateInformation;
import com.bridgelabz.fundoonotes.Response.Response;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.service.AmazonS3Service;
import com.bridgelabz.fundoonotes.service.UserService;

@PropertySource("classpath:message.properties") 
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice;

	@Autowired
	private AmazonS3Service amazonS3Service;

	@Autowired
	private Environment env;

	@PostMapping("/register")
	public ResponseEntity<Response> register(@Valid @RequestBody UserDTO userdto, BindingResult result)
			throws IOException {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));}
		User user = userservice.register(userdto);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 201, env.getProperty("register")));
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<Response> VerificationUser(@PathVariable("token") String token) throws Exception {
		User user = userservice.verifyUser(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 200, env.getProperty("verified")));
	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody UserInformation userinformation, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));
		}
		String user = userservice.userlogin(userinformation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 202, env.getProperty("login")));

	}

	@GetMapping("/getall")
	public ResponseEntity<Response> getallusers() {
		List<User> user = userservice.getall();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 200, env.getProperty("list")));
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Response> get(@PathVariable Long id) {
		User userobj = userservice.getUserById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(userobj, 200, env.getProperty("list")));
	}

	@PutMapping("/updateuser/{id}")
	public ResponseEntity<Response> update(@RequestBody updateInformation updateinformation, @PathVariable Long id,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));}
		User user = userservice.updateuser(updateinformation, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 200, env.getProperty("update")));

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable Long id) {
		userservice.deleteUser(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(204, env.getProperty("deleteuser")));
	}

	@PutMapping("/forgotpassword")
	public ResponseEntity<Response> setpassword(@RequestBody Updatepassword updatepassword, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Response(result.getAllErrors()));}
		User user = userservice.setnewpassword(updatepassword);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(user, 200, env.getProperty("setpassword")));

	}

	@PostMapping("/addprofilepic")
	public Map<String, String> uploadFile(@RequestPart(value = "file") MultipartFile file) {
		this.amazonS3Service.uploadFileToS3Bucket(file, true);
		Map<String, String> response = new HashMap<>();
		String filename=file.getOriginalFilename();
		response.put("message", "file [" + filename + "] uploading request submitted successfully.");
		return response;
	}

	@DeleteMapping("/deleteprofilepic")
	public Map<String, String> deleteFile(@RequestParam("file_name") String fileName) {
		this.amazonS3Service.deleteFileFromS3Bucket(fileName); 
		Map<String, String> response = new HashMap<>();
		response.put("message", "file [" + fileName + "] removing request submitted successfully.");
		return response;
	}

}
