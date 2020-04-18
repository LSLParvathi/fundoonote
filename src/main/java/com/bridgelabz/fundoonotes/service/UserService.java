package com.bridgelabz.fundoonotes.service;

import java.io.IOException;

import com.bridgelabz.fundoonotes.DTO.Updatepassword;
import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.model.User;

public interface UserService {

	User register(UserDTO userdto) throws IOException;

	User userLogin(UserInformation userinformation);

	User verifyUser(String token);

	User setNewPassword(Updatepassword updatepassword, String token);

}
