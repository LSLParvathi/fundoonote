package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.NoteDto; 
import com.bridgelabz.fundoonotes.DTO.UpdateNote;
import com.bridgelabz.fundoonotes.Exceptions.UserExceptions;
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
	@Autowired
	private UserService userservice;
	 
	@Transactional
	@Override
	public Note createNote(String token, NoteDto notedto) {
		String title = notedto.getTitle();
		Long id = ope.parseJWT(token);
		boolean Title = noterepository.getNoteByTitle(title).isPresent();
		User user = userservice.getUserById(id);
		if (Title == true) { throw new UserExceptions(null, 404, "title already exists or Id does not exists"); }
		else {
			BeanUtils.copyProperties(notedto, note);
			note.setArchive(false);
			note.setColours("black");
			note.setRemindme(LocalDateTime.now());
			note.setPin(true);
			note.setTrash(false);
			user.getNote().add(note);
			userrepository.save(user);
			return note;
		}
	}

	@Transactional
	@Override
	public Optional<List<Note>> getAllNotes() {
		Optional<List<Note>> note = noterepository.getAllNotes();
		if (note == null) { throw new UserExceptions(null, 404, "Note is Empty No Data is Existing");}
		else { return note; }
	}

	@Transactional
	@Override
	public Note getNoteById(Long note_id) {
		Note note = noterepository.getbyId(note_id)
				.orElseThrow(() -> new UserExceptions(null, 404, "no such id in the list"));
		return note;
	}
	
	@Transactional
	@Override
	public void deleteNote(Long note_id) {
		Note note = getNoteById(note_id); 
		noterepository.deletenote(note);
	}

	@Transactional
	@Override
	public Note updatenote(Long note_id, UpdateNote updatenote) {
		Note note = getNoteById(note_id);
		BeanUtils.copyProperties(updatenote, note);
		noterepository.saveNote(note);
		return note;
	}

	@Transactional
	@Override
	public Note Archive(Long note_id) {
		Note note = getNoteById(note_id);
		boolean s1 = note.isArchive();
		boolean s2 = note.isPin();
		if (s1 == true) { note.setArchive(false); }
		else if (s1 == true && s2 == true) { note.setArchive(false); } 
		else { note.setArchive(true); }
		noterepository.saveNote(note);
		return note;
	}

	@Transactional
	@Override
	public Note Pinned(Long note_id) {
		Note note = getNoteById(note_id);
		boolean s = note.isPin();
		if (s == true) { note.setPin(false); } 
		else { note.setPin(true); }
		return note;
	}
	
	@Transactional
	@Override
	public Optional<List<Note>> getAllNotesdeleted() {
		Optional<List<Note>> note = noterepository.getAllNotesDelete();
		if (note == null) { throw new UserExceptions(null, 404, "Note is Empty No Data is Existing");}
		else { return note; }
	}
	
	/*
	 * @Transactional
	 * 
	 * @Override public void deleteNote(Long note_id, TrashTable trashtable) { Note
	 * note = getNoteById(note_id); BeanUtils.copyProperties(trashtable, note);
	 * noterepository.deletenote(note); }
	 */
}
