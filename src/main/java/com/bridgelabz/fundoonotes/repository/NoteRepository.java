package com.bridgelabz.fundoonotes.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Note;

@Repository
public class NoteRepository {
	@Autowired
	private EntityManager entitymanager;

	public void saveNote(Note note) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.save(note);

	}

	public void deletenote(Note note) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.delete(note);

	}

	public List<Note> getAllNotesByUserId(Long id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query query = currentsession.createQuery("from Note where user_id=:id").setParameter("user_id", id);
		List<Note> note = query.getResultList();
		return note;
	}

	public Optional<Note> getNoteById(Long note_id, Long user_id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from Note where  note_id=:note_id and user_id=:user_id")
				.setParameter("note_id", note_id).setParameter("user_id", user_id).uniqueResultOptional();
	}

}
