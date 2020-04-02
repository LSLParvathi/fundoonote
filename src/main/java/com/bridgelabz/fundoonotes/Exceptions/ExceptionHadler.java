package com.bridgelabz.fundoonotes.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgelabz.fundoonotes.Response.Genericresponse;

@ControllerAdvice
public class ExceptionHadler {

	@ExceptionHandler(UserExceptions.class)
	public ResponseEntity<UserException> loginException(Genericresponse response) {
		UserException exp = new UserException();
		exp.setMessage(response.getMessage());
		exp.setData(response.getData());
		exp.setStatus(response.getStatus());

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(new UserException(exp.getData(), exp.getStatus(), exp.getMessage()));
	}

}
