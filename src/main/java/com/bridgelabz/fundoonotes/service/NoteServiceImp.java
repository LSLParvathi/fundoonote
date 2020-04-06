package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.SearchNote;
import com.bridgelabz.fundoonotes.DTO.UpdateNote;
import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
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
	private UserService userservice;
	@Autowired
	private Environment env;
	

	@Transactional
	@Override
	public Note createNote(String token, NoteDto notedto) throws NoteExceptions {
		Note note = new Note(); 
		String title = notedto.getTitle();
		Long id = ope.parseJWT(token);
		User user = userservice.getUserById(id);
		boolean Title = noterepository.getNoteByTitle(title).isPresent();
		if (Title == true) {
			throw new NoteExceptions(404,env.getProperty("exists"));
		} else {
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
	public List<Note> getAllNotes() throws NoteExceptions {
		List<Note> note = noterepository.getAllNotes();
		if (note == null) {
			throw new NoteExceptions(404, env.getProperty("nodata"));
		} else {
			return note;
		}
	}

	@Transactional
	@Override
	public Note getNoteById(Long note_id) throws NoteExceptions {
		Note note = noterepository.getbyId(note_id)
				.orElseThrow(() -> new NoteExceptions(404,env.getProperty("notexist")));
		System.out.println(note);
		return note;
	}

	@Transactional
	@Override
	public Note updatenote(Long note_id, UpdateNote updatenote) throws NoteExceptions {
		Note note = getNoteById(note_id);
		List<Note> notes = noterepository.getAllNotes();
		Note updateNote = (Note) notes.stream().filter(upnote -> upnote.getNote_id().equals(note_id))
				.collect(Collectors.toList());
		// BeanUtils.copyProperties(updatenote, note);
		noterepository.saveNote(note);
		return note;
	}

	@Transactional
	@Override
	public Note Archive(Long note_id) throws NoteExceptions {
		Note note = getNoteById(note_id);
		boolean s1 = note.isArchive();
		boolean s2 = note.isPin();
		if (s1 == true) {
			note.setArchive(false);
		} else if (s1 == true && s2 == true) {
			note.setArchive(false);
		} else {
			note.setArchive(true);
		}
		noterepository.saveNote(note);
		return note;
	}

	@Transactional
	@Override
	public Note Pinned(Long note_id) throws NoteExceptions {
		Note note = getNoteById(note_id);
		boolean s = note.isPin();
		if (s == true) {
			note.setPin(false);
		} else {
			note.setPin(true);
		}
		return note;
	}

	@Transactional
	@Override
	public void deleteNote(Long note_id) throws NoteExceptions {
		Note note = getNoteById(note_id);
		note.setTrash(true);
		noterepository.saveNote(note);
		noterepository.deletenote(note);
	}

	@Transactional
	@Override
	public Note remindMe(Long note_id, LocalDateTime remind) throws NoteExceptions {
		Note note = getNoteById(note_id);
		note.setRemindme(remind);
		noterepository.saveNote(note);
		return note;
	}

	@Transactional
	@Override
	public void deleteRem(Long note_id) throws NoteExceptions {
		Note note = getNoteById(note_id);
		note.setRemindme(LocalDateTime.now());
		noterepository.saveNote(note);
	}

	@Transactional
	@Override
	public ArrayList<Note> getNotesByTitleAndDescription(SearchNote searchnote) throws NoteExceptions {
		String title = searchnote.getTitle();
		System.out.println("wlecome");
		String description = searchnote.getDescription();
		Note note = noterepository.searchNoteByTitleAndDescription(title, description)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		return null;
	}

}
