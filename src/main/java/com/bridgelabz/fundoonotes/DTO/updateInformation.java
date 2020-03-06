package com.bridgelabz.fundoonotes.DTO;

public class updateInformation
{

	private String phonenumber;
	private String password;
	
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public updateInformation(String phonenumber, String password) {
	 
		this.phonenumber = phonenumber;
		this.password = password;
	}
	@Override
	public String toString() {
		return "updateInformation [phonenumber=" + phonenumber + ", password=" + password + "]";
	}
	
	
	
}
