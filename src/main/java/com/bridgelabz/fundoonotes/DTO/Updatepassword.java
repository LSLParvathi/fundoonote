package com.bridgelabz.fundoonotes.DTO;

public class Updatepassword 
{

	private String setpassword;
	private String confirmpassword;
	
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
	public Updatepassword(String setpassword, String confirmpassword) {
	 
		this.setpassword = setpassword;
		this.confirmpassword = confirmpassword;
	}
	
	
}
