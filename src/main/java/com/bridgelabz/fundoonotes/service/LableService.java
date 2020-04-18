package com.bridgelabz.fundoonotes.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoonotes.DTO.LableDto;
import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.Exceptions.LableException;
import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.Note;

public interface LableService {

	List<Lable> getallLables();

	Lable createLable(@Valid LableDto labledto, String token) throws LableException;

	Lable updateLables(UpdateLable updatelable, Long id,String token) throws LableException;

	void deleteLables(Long id,String token) throws LableException;

	Note AddLableToNote(String token, Long note_id, Long lable_id) throws NoteExceptions, LableException;

	void deleteLablesFromNote(Long note_id, Long lable_id,String token) throws NoteExceptions, LableException;

}
