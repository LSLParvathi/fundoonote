package com.bridgelabz.fundoonotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@Service
public class CollaboratorServiceImp implements CollaboratorService {

	@Autowired
	private NoteServiceImp noteservice;
	@Autowired
	private UserService userservice;
	Note note = new Note();
	User user = new User();
	@Autowired
	private JWToperations ope;
	@Autowired
	private NoteRepository noterepository;

	@Transactional
	@Override
	public Note AddCol(String email, Long id, String token) throws NoteExceptions {
		Long Id = ope.parseJWT(token);
		User user1 = userservice.getUserById(Id);
		Note note = noteservice.getNoteById(id);
		User user = userservice.getUserByMail(email);
		note.getCollaborator().add(user);
		noterepository.saveNote(note);
		return note;

	}

	@Transactional
	@Override
	public void delCollaborator(String email, String token, Long id) throws NoteExceptions {
		Long Id = ope.parseJWT(token);
		User user1 = userservice.getUserById(Id);
		Note note = noteservice.getNoteById(id);
		User user = userservice.getUserByMail(email);
		note.getCollaborator().remove(user);
	}

	@Transactional
	@Override
	public List<User> getAllColl(String token, Long id) throws NoteExceptions {
		Long Id = ope.parseJWT(token);
		User user1 = userservice.getUserById(Id);
		Note note = noteservice.getNoteById(id);
		List<User> coluser = note.getCollaborator();
		return coluser;
	}

}
