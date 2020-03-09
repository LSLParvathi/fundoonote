package com.bridgelabz.fundoonotes.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
 
public class UserDTO {

	private String firstname;
	private String lastname;
	private String mobilenumber;
	private String email;
	private String password;

}
