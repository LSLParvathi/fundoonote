package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.TrashTable;
import com.bridgelabz.fundoonotes.DTO.UpdateNote;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

public interface NoteService {

	List<Note> getAllNotes();

	Note createNote(String token, NoteDto notedto); 
	 
	Note getNoteById(Long note_id); 

	Note updatenote(Long note_id, UpdateNote updatenote);

	Note Archive(Long note_id);

	Note Pinned(Long note_id);

	Optional<List<Note>> getAllNotesdeleted(); 
	
	void deleteNote(Long note_id);

	Note remindMe(Long note_id, LocalDateTime remind);

	void deleteRem(Long note_id);
}
