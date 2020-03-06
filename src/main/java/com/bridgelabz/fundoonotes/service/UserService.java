package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

public interface UserService 
{

//	List<User> get();
//	
//	User get(Long id);
//	
//  void save(User user);
//   
//    void delete(Long id);

	User register(UserDTO userdto);

	User userlogin(UserInformation userinformation);

	 
}
