package com.bridgelabz.fundoonotes.service;

import com.bridgelabz.fundoonotes.DTO.CollaboratorDto;
import com.bridgelabz.fundoonotes.model.Collaborator;

public interface CollaboratorService 
{

	Collaborator addcol(Long id, CollaboratorDto collaboratordto);
	
}
