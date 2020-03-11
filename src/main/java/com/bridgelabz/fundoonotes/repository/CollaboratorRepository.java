package com.bridgelabz.fundoonotes.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Collaborator;
import com.bridgelabz.fundoonotes.model.Lable;

@Component
@Repository
public class CollaboratorRepository {

	@Autowired
	private EntityManager entitymanager;

	public void saveColl(Collaborator collaborator) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.save(collaborator);
	}

}
