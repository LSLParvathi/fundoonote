package com.bridgelabz.fundoonotes.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoonotes.DTO.LableDto;
import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.Note;

public interface LableService {

	List<Lable> getallLables();

	Lable createLable(@Valid NoteDto notedto, String token);

	Lable updateLables(UpdateLable updatelable, Long id);

	void deleteLables(Long id);

	Note AddLableToNote(String token, Long note_id, Long lable_id);

	void deleteLablesFromNote(Long note_id, Long lable_id);

}
