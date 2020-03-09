package com.bridgelabz.fundoonotes.service;

import javax.validation.Valid;

import com.bridgelabz.fundoonotes.DTO.LableDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.model.Lable;

public interface LableService 
{
  
	Lable getall();
	
	Lable createLable(@Valid LableDto labledto, String token);

	Lable updateLables(UpdateLable updatelable, Long id);

	void deleteLables(Long id);
	
	
}
