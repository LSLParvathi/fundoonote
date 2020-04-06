package com.bridgelabz.fundoonotes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String firstname;
	private String lastname;
	private String mobilenumber;
	private String email;
	private String password;
	
	
}
