package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.NoteDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.Exceptions.LableException;
import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.LableRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@Service
public class LableServiceImp implements LableService {
	@Autowired
	private JWToperations ope;
	@Autowired
	private UserService userservice;
	@Autowired
	private LableRepository lablerepository;
	@Autowired
	private UserRepository userrepository;
	Note note = new Note();
	@Autowired
	private NoteService noteservice;

	@Transactional
	@Override
	public List<Lable> getallLables() {
		List<Lable> lable = lablerepository.getAllLables();
		if (lable == null) {
			new LableException(404, "no data is existing");
		}
		return lable;
	}

	@Transactional
	@Override
	public Note AddLableToNote(String token, Long note_id, Long lable_id) throws NoteExceptions, LableException {
		Lable lable = lablerepository.getById(lable_id)
				.orElseThrow(() -> new LableException(404, "no such id is existing"));
		Note note = noteservice.getNoteById(note_id);
		note.getLable().add(lable);
		return note;
	}

	@Transactional
	@Override
	public Lable createLable(NoteDto notedto, String token) throws LableException {
		String title = notedto.getTitle();
		boolean Title = lablerepository.getLableByTitle(title).isPresent();
		Long id = ope.parseJWT(token);
		User user = userservice.getUserById(id);
		if (Title == true) {
			throw new LableException(404, "title is already existing");
		} else {
			Lable lable = new Lable();
			BeanUtils.copyProperties(notedto, lable);
			lable.setCreatedate(LocalDateTime.now());
			lable.setUpdatedate(LocalDateTime.now());
			user.getLable().add(lable);
			userrepository.save(user);
			return lable;
		}

	}

	@Transactional
	@Override
	public Lable updateLables(UpdateLable updatelable, Long id) throws LableException {
		Lable lable = lablerepository.getById(id).orElseThrow(() -> new LableException(404, "no such id is existing"));
		BeanUtils.copyProperties(updatelable, lable);
		lable.setUpdatedate(LocalDateTime.now());
		lablerepository.saveLable(lable);
		return lable;
	}

	@Transactional
	@Override
	public void deleteLables(Long id) throws LableException {
		Lable lable = lablerepository.getById(id).orElseThrow(() -> new LableException(404, "no such id is existing"));
		lablerepository.deleteLable(lable);
	}

	@Transactional
	@Override
	public void deleteLablesFromNote(Long note_id, Long lable_id) throws NoteExceptions, LableException {
		Lable lable = lablerepository.getById(lable_id)
				.orElseThrow(() -> new LableException(404, "no such id is existing"));
		Note note = noteservice.getNoteById(note_id);
		note.getLable().remove(lable);
	}

}
