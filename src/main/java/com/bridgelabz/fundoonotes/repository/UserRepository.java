package com.bridgelabz.fundoonotes.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

@Repository
public class UserRepository {
	@Autowired
	private EntityManager entitymanager;

	public Optional<User> getUserById(Long id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from User where id=:id").setParameter("id", id).uniqueResultOptional();

	}

	public void saveUser(User user) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.saveOrUpdate(user);
	}

	public Optional<User> getUserByMail(String mail) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from User where email=:email").setParameter("email", mail)
				.uniqueResultOptional();
	}

	public Optional<User> findUserByProfile(Long id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from User where id=:id").setParameter("id", id).uniqueResultOptional();

	}

	 

}
