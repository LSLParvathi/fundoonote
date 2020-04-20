package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.UpdateNote;
import com.bridgelabz.fundoonotes.exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.exceptions.UserExceptions;
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
	private Environment env;

	/*
	 * @Autowired private ElasticSearchService elasticsearchservice;
	 */
	@Transactional
	@Override
	public Note createNote(String token, NoteDto notedto) throws NoteExceptions {
		Note note = new Note();
		Long id = ope.parseJWT(token);
		User user = userrepository.getUserById(id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		BeanUtils.copyProperties(notedto, note);
		note.setColours("black");
		user.getNote().add(note);
		userrepository.saveUser(user);
		/*
		 * try { elasticsearchservice.createNote(note); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		return note;
	}

	@Transactional
	@Override
	public List<Note> getAllNotes(String token) throws NoteExceptions {
		Long id = ope.parseJWT(token);
		User user = userrepository.getUserById(id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		List<Note> note = noterepository.getAllNotesByUserId(id);
		if (note == null)
			throw new NoteExceptions(404, env.getProperty("nodata"));
		return note;
	}

	@Transactional
	@Override
	public Note getNoteById(Long note_id, String token) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User user = userrepository.getUserById(user_id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		Note note = noterepository.getNoteById(note_id, user_id)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		return note;
	}

	@Transactional
	@Override
	public Note updatenote(Long note_id, UpdateNote updatenote, String token) throws NoteExceptions {
		Long id = ope.parseJWT(token);
		User user = userrepository.getUserById(id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		List<Note> notes = noterepository.getAllNotesByUserId(id);
		if (notes == null)
			throw new NoteExceptions(404, env.getProperty("nodata"));
		Note note = notes.stream().filter(upnote -> upnote.getNote_id().equals(note_id)).findAny()
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		BeanUtils.copyProperties(updatenote, note);
		noterepository.saveNote(note);
		/*
		 * try { elasticsearchservice.upDateNote(note); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		return note;
	}

	@Transactional
	@Override
	public Note Archive(Long note_id, String token) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User user = userrepository.getUserById(user_id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		Note note = noterepository.getNoteById(note_id, user_id)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		if (note.isArchive()) {
			note.setArchive(false);
		} else if (note.isArchive() && note.isPin()) {
			note.setArchive(false);
		} else {
			note.setArchive(true);
		}
		noterepository.saveNote(note);
		return note;
	}

	@Transactional
	@Override
	public Note Pinned(Long note_id, String token) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User user = userrepository.getUserById(user_id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		Note note = noterepository.getNoteById(note_id, user_id)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		if (note.isPin()) {
			note.setPin(false);
		}
		note.setPin(true);
		return note;
	}

	@Transactional
	@Override
	public void deleteNote(Long note_id, String token) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User user = userrepository.getUserById(user_id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		Note note = noterepository.getNoteById(note_id, user_id)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		note.setTrash(true);
		noterepository.saveNote(note);
		noterepository.deletenote(note);
		/*
		 * try { elasticsearchservice.deleteNote(String.valueOf(note_id)); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

	}

	@Transactional
	@Override
	public Note remindMe(Long note_id, LocalDateTime remind, String token) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User user = userrepository.getUserById(user_id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		Note note = noterepository.getNoteById(note_id, user_id)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		note.setRemindme(remind);
		noterepository.saveNote(note);
		return note;
	}

	@Transactional
	@Override
	public void deleteRem(Long note_id, String token) throws NoteExceptions {
		Long user_id = ope.parseJWT(token);
		User user = userrepository.getUserById(user_id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		Note note = noterepository.getNoteById(note_id, user_id)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		note.setRemindme(LocalDateTime.now());
		noterepository.saveNote(note);
	}

}
