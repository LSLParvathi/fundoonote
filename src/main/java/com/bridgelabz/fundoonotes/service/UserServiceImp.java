package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.Exceptions.UserExceptions;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.utilis.JMSoperations;
import com.bridgelabz.fundoonotes.utilis.JWToperations;
import com.bridgelabz.fundoonotes.utilis.UserResponse;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import java.util.Optional;

import javax.security.auth.login.LoginException;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private JWToperations ope;
	@Autowired
	private JMSoperations ope1;
	 

	@Override
	public User register(UserDTO userdto) {
		User user = new User();
		BeanUtils.copyProperties(userdto, user);
		boolean n = userrepository.IfEmailExists(user.getEmail()).isPresent();
		if (n == true) {
			throw new UserExceptions(null, 404, "email already exists");
		} else {
			BCryptPasswordEncoder by = new BCryptPasswordEncoder();
			String newpwd = by.encode(user.getPassword());
			user.setPassword(newpwd);

			user.setCreatedate(LocalDateTime.now());
			user.setUpdatedate(LocalDateTime.now());
			user.setVerify(false);

			userrepository.save(user);

			String str = "http://localhost:8080/verify/" + ope.JWTToken(user.getId());
			ope1.sendEmail(user.getEmail(), "Verify Email", str);

			return user;
		}
	}

	@Override
	public User userlogin(UserInformation userinformation) {
		User user = userrepository.IfEmailExists(userinformation.getEmail()).orElseThrow(() ->new UserExceptions(null, 404, "email does not exists"));
		String password = userinformation.getPassword();
		System.out.println("password is: "+password);
		String newpassword = user.getPassword();
		BCryptPasswordEncoder by = new BCryptPasswordEncoder();
		boolean c = by.matches(password, newpassword);
		System.out.println("c is: "+c);
		if ( c ==  true)
		{
			return user;
		 
		}
		else {
			throw new UserExceptions(null, 404, "password is incorrect");
		} 
	}
}
//		if (userrepository.IfEmailExists(user.getEmail()) == null) {
//			System.out.println("email does note exists");
//			String newpwd = by.encode(user.getPassword());
//			System.out.println("old password is: " + user.getPassword());
//			System.out.println("new password is: " + newpwd);
//			System.out.println("id is: " + user.getId());
//			user.setPassword(newpwd);
//			userrepository.save(user);
//			String str = "http://localhost:8080/verify/" + ope.JWTToken(user.getId());
//			ope1.sendEmail(user.getEmail(), "Verify Email", str);
//			return user;
//		} else {
//			System.out.println("returning null---");
//			return null;
//		}

//	@Override
//	public List<User> get() {
//		return userrepository.get();
//	}
//
//	 
//	@Override
//	public User get(Long id) {
//		return userrepository.get(id);
//	}
//
//	 
//
//	 
//	@Override
//	public void delete(Long id) {
//		userrepository.delete(id);
//	}
