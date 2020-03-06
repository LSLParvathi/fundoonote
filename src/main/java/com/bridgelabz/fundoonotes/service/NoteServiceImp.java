package com.bridgelabz.fundoonotes.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@Service
public class NoteServiceImp implements NoteService {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private NoteRepository noterepository;
	@Autowired
	private JWToperations ope;
	@Autowired
	private NoteService noteservice;
	@Autowired
	private Note note;

	@Override
	public Note createNote(String token, NoteDto notedto) {
		// User user = new User();
		Long id = ope.parseJWT(token);
		// user = userrepository.get(id);
		// System.out.println(user.getId() + "check here");
		System.out.println("the details are: " + notedto.getDescription() + " " + notedto.getTitle());
		if (userrepository.verify(id) == true) {
			BeanUtils.copyProperties(notedto, note);
			note.setArchive("yes");
			note.setColours("blue");
			note.setRemindme("tommarrow");
			note.setVerify(true);
//			user.getNote().add(note);
//			userrepository.save(user);
			noterepository.saveNote(note);

			return note;
		} else {
			return null;
		}

	}

	@Override
	public List<Note> getAllNotes() {
		return noterepository.getAllNotes();
	}

	@Override
	public Note get(Long note_id) {
		return noterepository.getbyId(note_id);
		 
	}

	@Override
	public void delete(Long note_id) {
		 
		
	}

}
