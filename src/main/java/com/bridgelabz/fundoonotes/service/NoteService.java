package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

public interface NoteService {

	List<Note> getAllNotes();

	Note createNote(String token, NoteDto notedto);

	Note get(Long note_id);

	void delete(Long note_id);
}
