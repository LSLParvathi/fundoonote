package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.model.Note;

public interface NoteService {

	List<Note> getAllNotes();

	Note createNote(String token, NoteDto notedto);

}
