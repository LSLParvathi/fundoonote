package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.utilis.JMSoperations;
import com.bridgelabz.fundoonotes.utilis.JWToperations;
import com.bridgelabz.fundoonotes.utilis.UserResponse;
import com.bridgelabz.fundoonotes.repository.UserRepository;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private JWToperations ope;

	
	
	@Transactional
	@Override
	public List<User> get() {
		return userrepository.get();
	}

	@Transactional
	@Override
	public User get(Long id) {
		return userrepository.get(id);
	}

	@Transactional
	@Override
	public void save(User user) {
		userrepository.save(user);
	}

	@Transactional
	@Override
	public void delete(Long id) {
		userrepository.delete(id);
	}
	
	 
	
	 

}