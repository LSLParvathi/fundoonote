package com.bridgelabz.fundoonotes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;

public interface CollaboratorService 
{

	Note AddCol(String email, Long id, String token) throws NoteExceptions; 

	void delCollaborator(String email, String token, Long id) throws NoteExceptions;

	List<User> getAllColl(String token, Long id) throws NoteExceptions;
 
}