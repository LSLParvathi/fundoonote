package com.bridgelabz.fundoonotes.utilis;

public class UserResponse {
	
	private Object data;
	private int i;
	private String str;

	public UserResponse() {

	}

	public UserResponse(Object data, int i, String str) {
	 
		this.data = data;
		this.i = i;
		this.str = str;
	}

	public UserResponse(int i, String str) {
	 
		this.i = i;
		this.str = str;
	}

	@Override
	public String toString() {
		return "UserResponse [data=" + data + ", i=" + i + ", str=" + str + "]";
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	} 

}
