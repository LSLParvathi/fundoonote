package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;

import com.bridgelabz.fundoonotes.dto.NoteDto;
import com.bridgelabz.fundoonotes.dto.UpdateNote;
import com.bridgelabz.fundoonotes.exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.model.Note;

public interface NoteService {

	Note createNote(String token, NoteDto notedto) throws NoteExceptions;

	Note getNoteById(Long note_id, String token) throws NoteExceptions;

	Note updatenote(Long note_id, UpdateNote updatenote, String token) throws NoteExceptions;

	Note Archive(Long note_id, String token) throws NoteExceptions;

	Note Pinned(Long note_id, String token) throws NoteExceptions;

	void deleteNote(Long note_id, String token) throws NoteExceptions;

	Note remindMe(Long note_id, LocalDateTime remind, String token) throws NoteExceptions;

	void deleteRem(Long note_id, String token) throws NoteExceptions;

	List<Note> getAllNotes(String token) throws NoteExceptions;

}
