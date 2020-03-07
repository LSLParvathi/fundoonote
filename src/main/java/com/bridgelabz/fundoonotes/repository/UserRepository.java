package com.bridgelabz.fundoonotes.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.EntityManager;
import javax.validation.valueextraction.Unwrapping.Unwrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.Exceptions.UserExceptions;
import com.bridgelabz.fundoonotes.controller.UserController;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.utilis.UserResponse;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@Component
@Repository
public class UserRepository {
	@Autowired
	private EntityManager entitymanager;

	public void save(User user) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.saveOrUpdate(user);
	}

	public Optional<User> IfEmailExists(String email) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from User where email=:email").setParameter("email", email)
				.uniqueResultOptional();

	}

	public Optional<User> checkuser(String email, String password) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from User where email=:email").setParameter("email", email)
				.uniqueResultOptional();
	}

	public Optional<User> get() {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from User").uniqueResultOptional();

	}

	public Optional<User> get(Long id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from User where id=:id").setParameter("id", id).uniqueResultOptional();

	}

	public void saveUser(User user) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.saveOrUpdate(user);
	}

	public void deleteUser(User user) {
		Session currentsession = entitymanager.unwrap(Session.class); 
		currentsession.delete(user);
	}

	public Optional<User> getUserByMail(String mail) {
		 Session currentsession = entitymanager.unwrap(Session.class);
		 return currentsession.createQuery("from User where email=:email").setParameter("email", mail)
					.uniqueResultOptional();
	}

}
