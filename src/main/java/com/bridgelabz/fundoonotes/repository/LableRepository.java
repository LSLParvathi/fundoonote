package com.bridgelabz.fundoonotes.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

 @Repository
public class LableRepository {
	@Autowired
	private EntityManager entitymanager;

	public Optional<Lable> getLableByTitle(String title) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from Note where  title=:title").setParameter("title", title)
				.uniqueResultOptional();
	}

	public List<Lable> getAllLables() {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query query = currentsession.createQuery("from Lable");
		List<Lable> lable = query.getResultList();
		return lable;

	}

	public Optional<Lable> getById(Long id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from Lable where id=:id").setParameter("id", id).uniqueResultOptional();
	}

	public void saveLable(Lable lable) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.save(lable);
	}

	public void deleteLable(Lable lable) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.delete(lable);
	}

	public  List<Lable> getLableByUserId(Long user_id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query query = currentsession.createQuery("from Lable where user_id=:user_id").setParameter("user_id", user_id);
 		List<Lable> lable = query.getResultList();
		return lable;
	}
}
