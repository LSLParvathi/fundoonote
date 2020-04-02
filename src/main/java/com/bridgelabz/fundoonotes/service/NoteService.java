package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.SearchNote; 
import com.bridgelabz.fundoonotes.DTO.UpdateNote;
import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

public interface NoteService {

	List<Note> getAllNotes() throws NoteExceptions;

	Note createNote(String token, NoteDto notedto) throws NoteExceptions; 
	 
	Note getNoteById(Long note_id) throws NoteExceptions; 

	Note updatenote(Long note_id, UpdateNote updatenote) throws NoteExceptions;

	Note Archive(Long note_id) throws NoteExceptions;

	Note Pinned(Long note_id) throws NoteExceptions; 
	
	void deleteNote(Long note_id) throws NoteExceptions;

	Note remindMe(Long note_id, LocalDateTime remind) throws NoteExceptions;

	void deleteRem(Long note_id) throws NoteExceptions; 
	List<Note> getNotesByTitleAndDescription(SearchNote searchnote) throws NoteExceptions;
}
