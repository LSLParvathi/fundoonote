package com.bridgelabz.fundoonotes.service;

import java.io.IOException;

import com.bridgelabz.fundoonotes.dto.Updatepassword;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.dto.UserInformation;
import com.bridgelabz.fundoonotes.model.User;

public interface UserService {

	User register(UserDto userdto) throws IOException;

	User userLogin(UserInformation userinformation);

	User verifyUser(String token);

	User setNewPassword(Updatepassword updatepassword, String token);

}
