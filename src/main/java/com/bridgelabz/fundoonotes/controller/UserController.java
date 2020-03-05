package com.bridgelabz.fundoonotes.controller;

import java.security.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.utilis.UserResponse;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.service.UserService;
import com.bridgelabz.fundoonotes.utilis.JMSoperations;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userservice;
	@Autowired
	private JMSoperations ope1 ;
	@Autowired
	private UserRepository userrepository ;
	@Autowired
	private User user;
	@Autowired
	private JWToperations ope;
	
	@GetMapping("/getall")
	public List<User> get() {
		return userservice.get();

	}

	@GetMapping("/get/{id}")
	public User get(@PathVariable Long id) {
		User userobj = userservice.get(id);
		if (userobj == null) {
			throw new RuntimeException("User with such id: " + id + " does not exist.");
		}
		return userobj;

	}

	@PostMapping("/register")
	public User save(@RequestBody UserDTO userdto) {
		 
		User user = new User(); 
		BeanUtils.copyProperties(userdto, user);
		user.setDate(LocalDateTime.now());
		user.setVerify(false);
		userservice.save(user);
		String str = "http://localhost:8080/verify/" + ope.JWTToken(user.getId()); 
		ope.sendEmail(user.getEmail(), "Verify Email", str);
		return user;
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<UserResponse> VerificationUser(@PathVariable("token") String token) throws Exception {

		UserRepository userrepository = new UserRepository();
		JWToperations ope = new JWToperations();
		Long id = ope.parseJWT(token);
		User user = userservice.get(id);
		if (userrepository.verify(id) == true) {
			user.setVerify(true);
			update(user);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(token, 200, "verified"));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(token, 401, "not verified"));

	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		userservice.delete(id);
		return "the user with id " + id + " has been deleted.";
	}

	@PutMapping("/upd")
	public User update(@RequestBody User user) {
		user.setDate(LocalDateTime.now());
		user.setVerify(true);
		userservice.save(user);
		System.out.println(user.getEmail()+"--"+user.getFirstname()+" ----"+user.getLastname()+"---"+user.getId()+"---"+user.getDate()+"----"+user.getVerify());
		userservice.save(user);
		return user;

	}

	@PutMapping("/forgot/{id}")
	public String setnewpassword(@PathVariable Long id, @RequestBody User log) {
		User user = new User();
		JWToperations ope = new JWToperations();
		BeanUtils.copyProperties(log, user);
		userservice.delete(id);
		String mail = "mangalagirileela1997@gmail.com";
		System.out.println("the id is : " + id);
		String newpwd = "1997";
		user.setDate(LocalDateTime.now());
		user.setPassword(newpwd);
		userservice.save(user);
		update(user);
		String str = "http://localhost:8080/forgotpassword/" + "resetpassword";
		ope.sendEmail(mail, "Verify Email", str);
		return "link has been send to this mail " + mail + " to set new password";
	}

	@GetMapping("/forgotpassword/{id}")
	public ResponseEntity<UserResponse> setnewpassword(@RequestBody User user, @PathVariable Long id) throws Exception {
		UserRepository userrepository = new UserRepository();
		String mail = "mangalagirileela1997@gmail.com";
		System.out.println("id is: " + id);
		if (userrepository.verify(mail) == true) {

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse("old", 200, "password is set"));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse("new", 401, "reset password"));

	}

}
