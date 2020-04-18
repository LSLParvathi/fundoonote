package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

public interface CollaboratorService {

	Note AddCol(String email, Long note_id, String token) throws NoteExceptions;

	void delCollaborator(String token, Long note_id) throws NoteExceptions;

	List<User> getAllColl(String token) throws NoteExceptions;

}