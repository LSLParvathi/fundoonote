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

	public Long parseJWT(String jwt){
		Long userId = (long) 0;
		if (jwt != null) {
			userId = JWT.require(Algorithm.HMAC256(secret)).build().verify(jwt).getClaim("id").asLong();
		}
		return userId;
	}


	public static void sendEmail(String toEmail, String subject, String body) {
		String fromEmail = System.getenv("email");
		String password  = System.getenv("password");
		Properties pro   = new Properties();
		pro.put("mail.smtp.auth", "true");
		pro.put("mail.smtp.starttls.enable", "true");
		pro.put("mail.smtp.host", "smtp.gmail.com");
		pro.put("mail.smtp.port", "587");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}

		};
		Session session = Session.getInstance(pro, auth);
		send(session, fromEmail, toEmail, subject, body);

	}

	private static void send(Session session, String fromEmail, String toEmail, String subject, String body) {
		MimeMessage message = new MimeMessage(session); 
		
		try { 
			message.setFrom(new InternetAddress(fromEmail, "leela"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception occured while sending mail");
		}
	}
	 
}
