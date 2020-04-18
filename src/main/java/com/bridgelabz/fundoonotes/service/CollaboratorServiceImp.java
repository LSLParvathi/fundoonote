package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.exceptions.UserExceptions;
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
	private UserRepository userrepository;
	@Autowired
	private JWToperations ope;
	@Autowired
	private NoteRepository noterepository;
	@Autowired
	private Environment env;

	@Transactional
	@Override
	public Note AddCol(String email, Long note_id, String token) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User collaborator = userrepository.getUserByMail(email)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("notexist")));
		List<Note> notes = noterepository.getAllNotesByUserId(user_id);
		if (notes == null)
			throw new NoteExceptions(404, env.getProperty("nodata"));
		Note note = notes.stream().filter(upnote -> upnote.getNote_id().equals(note_id)).findAny()
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		note.getCollaborator().add(collaborator);
		noterepository.saveNote(note);
		return note;

	}

	@Transactional
	@Override
	public void delCollaborator( String token, Long note_id) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User  collaborator = userrepository.getUserById(user_id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		List<Note> notes = noterepository.getAllNotesByUserId(user_id);
		if (notes == null)
			throw new NoteExceptions(404, env.getProperty("nodata"));
		Note note = notes.stream().filter(upnote -> upnote.getNote_id().equals(note_id)).findAny()
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist"))); 
			note.getCollaborator().remove(collaborator);
	}

	@Transactional
	@Override
	public  List<User> getAllColl(String token) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User user = userrepository.getUserById(user_id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		 return user.getCollaborator();
	}

}
