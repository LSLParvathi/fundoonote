package com.bridgelabz.fundoonotes.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.User;

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

	public List<User> get() {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query query = currentsession.createQuery("from User");
		List<User> user = query.getResultList();
		return user;
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

	public ArrayList<User> getAllUsers() {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query query = currentsession.createQuery("from User");
		ArrayList<User> user = (ArrayList<User>) query.getResultList();
		return user;
	}

	public Optional<User> findUserByProfile(Long id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from User where id=:id").setParameter("id", id).uniqueResultOptional();

	}

}
