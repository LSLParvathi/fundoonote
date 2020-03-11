package com.bridgelabz.fundoonotes.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.CollaboratorDto;
import com.bridgelabz.fundoonotes.model.Collaborator;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.repository.CollaboratorRepository;
import com.bridgelabz.fundoonotes.repository.NoteRepository;

@Service
public class CollaboratorServiceImp implements CollaboratorService{
	
	@Autowired
	private NoteService noteservice;
	@Autowired
	private NoteRepository noterepository;
	@Autowired
	private Collaborator collaborator;
	@Autowired
	private CollaboratorRepository collaboratorrepository ;
	@Autowired
	private CollaboratorService colservice;

	@Transactional
	@Override
	public Collaborator addcol(Long id, CollaboratorDto collaboratordto) { 
		Note note = noteservice.getNoteById(id);
		BeanUtils.copyProperties(collaboratordto, collaborator);
		note.getCollaborator().add(collaborator);
		collaboratorrepository.saveColl(collaborator);
		return collaborator;
	} 
	 
}
