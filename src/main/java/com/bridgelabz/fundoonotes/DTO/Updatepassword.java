package com.bridgelabz.fundoonotes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Updatepassword {
	private String setpassword;
	private String confirmpassword;
	private String mail;

}
