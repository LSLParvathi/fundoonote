package com.bridgelabz.fundoonotes.DTO;

 
public class UserInformation {
	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserInformation(String email, String password) {

		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInformation [email=" + email + ", password=" + password + "]";
	}

}