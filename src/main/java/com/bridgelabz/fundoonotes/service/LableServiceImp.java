package com.bridgelabz.fundoonotes.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.LableDto;
import com.bridgelabz.fundoonotes.DTO.UpdateLable;
import com.bridgelabz.fundoonotes.Exceptions.LableException;
import com.bridgelabz.fundoonotes.Exceptions.NoteExceptions;
import com.bridgelabz.fundoonotes.Exceptions.UserExceptions;
import com.bridgelabz.fundoonotes.model.Lable;
import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.LableRepository;
import com.bridgelabz.fundoonotes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@Service
public class LableServiceImp implements LableService {
	@Autowired
	private JWToperations ope;
	@Autowired
	private LableRepository lablerepository;
	@Autowired
	private UserRepository userrepository;
	@Autowired
	private NoteRepository noterepository;
	@Autowired
	private Environment env;

	@Transactional
	@Override
	public Lable createLable(LableDto labledto, String token) throws LableException {
		Long id = ope.parseJWT(token);
		User user = userrepository.getUserById(id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		Lable lable = new Lable();
		BeanUtils.copyProperties(labledto, lable);
		lable.setCreatedate(LocalDateTime.now());
		lable.setUpdatedate(LocalDateTime.now());
		user.getLable().add(lable);
		userrepository.saveUser(user);
		return lable;

	}

	@Transactional
	@Override
	public Note AddLableToNote(String token, Long note_id, Long lable_id) throws NoteExceptions, LableException {
		Long user_id = ope.parseJWT(token);
		Note note = noterepository.getNoteById(note_id, user_id)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		Lable lable = lablerepository.getById(lable_id)
				.orElseThrow(() -> new LableException(404, env.getProperty("notexist")));
		note.getLable().add(lable);
		return note;
	}

	@Transactional
	@Override
	public void deleteLablesFromNote(Long note_id, Long lable_id, String token) throws NoteExceptions, LableException {
		Long user_id = ope.parseJWT(token);
		Note note = noterepository.getNoteById(note_id, user_id)
				.orElseThrow(() -> new NoteExceptions(404, env.getProperty("notexist")));
		Lable lable = lablerepository.getById(lable_id)
				.orElseThrow(() -> new LableException(404, env.getProperty("notexist")));
		note.getLable().remove(lable);
	}

	@Transactional
	@Override
	public List<Lable> getallLables() {
		List<Lable> lable = lablerepository.getAllLables();
		if (lable == null) {
			new LableException(404, env.getProperty("notexist"));
		}
		return lable;
	}

	@Transactional
	@Override
	public Lable updateLables(UpdateLable updatelable, Long id, String token) throws LableException {
		List<Lable> lable1 = lablerepository.getLableByUserId(ope.parseJWT(token));
		if(lable1 == null) throw new LableException(404, env.getProperty("notexist"));
		Lable lable = lable1.stream().filter(Lid -> Lid.getId().equals(id)).findAny().orElseThrow(() -> new LableException(404, env.getProperty("notexist")));
		BeanUtils.copyProperties(updatelable, lable);
		lable.setUpdatedate(LocalDateTime.now());
		lablerepository.saveLable(lable);
		return lable;
	}

	@Transactional
	@Override
	public void deleteLables(Long id, String token) throws LableException {
		User user = userrepository.getUserById(ope.parseJWT(token))
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata"))); 
		List<Lable> lable1 = lablerepository.getLableByUserId(ope.parseJWT(token));
		if(lable1 == null) throw new LableException(404, env.getProperty("notexist"));
		Lable lable = lable1.stream().filter(Lid -> Lid.getId().equals(id)).findAny().orElseThrow(() -> new LableException(404, env.getProperty("notexist")));
 		user.getLable().remove(lable); 
	}

}
