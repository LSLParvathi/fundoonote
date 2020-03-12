package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bridgelabz.fundoonotes.DTO.Updatepassword;
import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.DTO.updateInformation;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

public interface UserService 
{
 

	User register(UserDTO userdto);

	String userlogin(UserInformation userinformation);

	List<User> getall();

	User getUserById(Long id);

	User verifyUser(String token);

	User updateuser(updateInformation updateinformation,Long id);

	void deleteUser(Long id);

	User setnewpassword(Updatepassword updatepassword);

	 User getUserByMail(String mail);
}
