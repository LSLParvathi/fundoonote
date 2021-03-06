package com.bridgelabz.fundoonotes.exceptions;

public class LableException extends Exception {

	private Object data;
	private int status;
	private String message;

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

	public LableException(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public LableException(Object data, int status, String message) {
		this.data = data;
		this.status = status;
		this.message = message;
	}

	public LableException() {
	}

}
