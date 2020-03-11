package com.bridgelabz.fundoonotes.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoonotes.DTO.LableDto;
import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.model.Lable;

public interface LableService 
{
  
	List<Lable> getallLables();
	
	Lable createLable(@Valid NoteDto notedto, String token);

	Lable updateLables(UpdateLable updatelable, Long id);

	void deleteLables(Long id);

	Lable AddNoteToLable(NoteDto notedto, String token);
	
	
}
