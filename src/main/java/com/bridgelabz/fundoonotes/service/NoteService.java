package com.bridgelabz.fundoonotes.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.UpdateNote;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

public interface NoteService {

	Optional<List<Note>> getAllNotes();

	Note createNote(String token, NoteDto notedto); 
	 
	Note getNoteById(Long note_id);
 
	void deleteNote(Long note_id);

	Note updatenote(Long note_id, UpdateNote updatenote);
}
