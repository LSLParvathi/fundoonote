package com.bridgelabz.fundoonotes.utilis;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Component
public class JWToperations {

	private static final String secret = "6360803337";

	public String JWTToken(Long userId) {
		String token = null;
		try {
			token = JWT.create().withClaim("id", userId).sign(Algorithm.HMAC256(secret));
		} catch (IllegalArgumentException | JWTCreationException e) {
			e.printStackTrace();
		}
		return token;
	}

	public Long parseJWT(String jwt) {
		Long userId = (long) 0;
		if (jwt != null) {
			userId = JWT.require(Algorithm.HMAC256(secret)).build().verify(jwt).getClaim("id").asLong();
		}
		return userId;
	}

}
