package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.bridgelabz.fundoonotes.DTO.LableDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.DTO.Updatepassword;
import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.DTO.updateInformation;
import com.bridgelabz.fundoonotes.Exceptions.UserExceptions;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.LableRepository;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utilis.JWToperations;
import com.bridgelabz.fundoonotes.utilis.UserResponse;

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
	@Autowired
	private NoteRepository noterepository;
	@Autowired
	private Note note;

	@Transactional
	@Override
	public Lable getall() {
		Lable lable = lablerepository.get().orElseThrow(() -> new UserExceptions(null, 404, "no data is existing"));
		return lable;
	}

	@Transactional
	@Override
	public Lable createLable(LableDto labledto, String token) {
		System.out.println("888888888888in88888888");
		String title = labledto.getTitle();
		System.out.println("8888title8888"+title);
		boolean Title = lablerepository.getLableByTitle(title).isPresent();
		System.out.println("8888token888"+token);
		Long id = ope.parseJWT(token);
		System.out.println("8888id8888"+id);
		User user = userservice.getUserById(id);
		 
		if (Title == true) {
			throw new UserExceptions(null, 404, "title is already existing");
		} else {
			Lable lable = new Lable();
			BeanUtils.copyProperties(labledto, lable);
			lable.setCreatedate(LocalDateTime.now());
			lable.setUpdatedate(LocalDateTime.now());
			note.getLable().add(lable);
			user.getNote().add(note);
			userrepository.save(user);
			return lable;
		}

	}

	@Transactional
	@Override
	public Lable updateLables(UpdateLable updatelable, Long id) {
		Lable lable = lablerepository.getById(id)
				.orElseThrow(() -> new UserExceptions(null, 404, "no such id is existing"));
		BeanUtils.copyProperties(updatelable, lable);
		lable.setUpdatedate(LocalDateTime.now());
		lablerepository.saveLable(lable);
		return lable;
	}

	@Transactional
	@Override
	public void deleteLables(Long id) {
		Lable lable = lablerepository.getById(id)
				.orElseThrow(() -> new UserExceptions(null, 404, "no such id is existing"));
		lablerepository.deleteLable(id);
	}

}
