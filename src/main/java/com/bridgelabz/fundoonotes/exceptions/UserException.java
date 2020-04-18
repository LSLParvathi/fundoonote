package com.bridgelabz.fundoonotes.exceptions;
public class UserException 
{

	private Object data;
	private int status;
	private String message;
	private String token;
	
	public UserException() {
		 
	}
	public UserException(Object data, int status, String message) {
		 
		this.setData(data);
		this.setStatus(status);
		this.setMessage(message);
	}
	
	public UserException(Object data, int status, String message, String token) {
		super();
		this.data = data;
		this.status = status;
		this.message = message;
		this.token = token;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}

