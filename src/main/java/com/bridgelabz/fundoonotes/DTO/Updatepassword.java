package com.bridgelabz.fundoonotes.DTO;

public class Updatepassword 
{

	private String setpassword;
	private String confirmpassword;
	private String mail;
	
	public String getSetpassword() {
		return setpassword;
	}
	public void setSetpassword(String setpassword) {
		this.setpassword = setpassword;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	 
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Updatepassword(String setpassword, String confirmpassword, String mail) {
	 
		this.setpassword = setpassword;
		this.confirmpassword = confirmpassword;
		this.mail = mail;
	}
	
	
}
