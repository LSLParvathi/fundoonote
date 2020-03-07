package com.bridgelabz.fundoonotes.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

@Component
@Repository
public class NoteRepository {

	@Autowired
	private EntityManager entitymanager;

	public void saveNote(Note note) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.save(note);
	}

	public Optional<List<Note>> getAllNotes() {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from Note").uniqueResultOptional();
	}

	public Optional<Note> getbyId(Long note_id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from Note where  note_id=:note_id").setParameter("note_id", note_id)
				.uniqueResultOptional();
	}

	public Optional<Note> getNoteByTitle(String title) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from Note where  title=:title").setParameter("title", title)
				.uniqueResultOptional();
	}

	public void deletenote(Note note) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.delete(note);

	}

	public Optional<List<Note>> getAllNotesDelete() {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from TrashTable").uniqueResultOptional();
	}
}
