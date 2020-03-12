package com.bridgelabz.fundoonotes.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class CollaboratorRepository {

	@Autowired
	private EntityManager entitymanager;
	
	
}
