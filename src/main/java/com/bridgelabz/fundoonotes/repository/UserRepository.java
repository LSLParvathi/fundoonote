package com.bridgelabz.fundoonotes.repository;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import javax.persistence.EntityManager;
import javax.validation.valueextraction.Unwrapping.Unwrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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

	public List<User> get() {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query<User> query = currentsession.createQuery("from User", User.class);
		List<User> list = query.getResultList();
		return list;

	}

	public User get(long id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		User userobj = currentsession.get(User.class, id);
		return userobj;
	}

	public void save(User user) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.saveOrUpdate(user);
	}

	public void delete(long id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		User userobj = currentsession.get(User.class, id);
		currentsession.delete(userobj);
	}

	public boolean verify(Long id) {
		/*
		 * System.out.println("check here"); List<User> users=get(); for(User b : users)
		 * { System.out.println("******* the data is: "+b); if(b.getId() == id) { return
		 * true; } } return false;
		 */
		return true;
	}

	/*
	 * public int getbyemail(String email) { Session currentsession =
	 * entitymanager.unwrap(Session.class); Query<User> query = currentsession.
	 * createQuery("from User where email = 'managalagirileela1997@gmail.com'",User.
	 * class); List<User> list = query.getResultList(); for (User s : list) {
	 * return(s.getId()); } return 0; }
	 */

	public boolean verify(String email) {
		return true;
	}
 
 
}
