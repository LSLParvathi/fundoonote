package com.bridgelabz.fundoonotes.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Note;

@Component
@Repository
public class NoteRepository {

	@Autowired
	private EntityManager entitymanager;

	public void saveNote(Note note) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.save(note);

	}

	public List<Note> getAllNotes() {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query<Note> query = currentsession.createQuery("from Note", Note.class);
		List<Note> list = query.getResultList();
		return list;
	}

	public boolean verify(Long id) {

		return true;
	}

	public Note getbyId(Long note_id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		Note note = currentsession.get(Note.class, note_id);
		return note;

	}
}
