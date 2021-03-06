package com.bridgelabz.fundoonotes.response;

import java.util.List;

import org.springframework.validation.ObjectError;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response {

	private Object data;
	private int status;
	private String message;
	private String token;
	
	

	public Response(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public Response(Object data, int status, String message) {
		this.data = data;
		this.status = status;
		this.message = message;
	}

	public Response(Object data,  String message) {
		this.data = data; 
		this.message = message;
	}
	public Response( List<ObjectError> allErrors) {
	}

	public Response(String message) {  
		this.message = message;
	}

	public Response(String user, String urlPath, int i, String property) {
 	}

	public Response(Object data, String message, String token) {
		super();
		this.data = data;
		this.message = message;
		this.token = token;
	}

}
