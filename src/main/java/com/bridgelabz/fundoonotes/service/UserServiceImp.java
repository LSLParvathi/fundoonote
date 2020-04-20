package com.bridgelabz.fundoonotes.service;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.dto.Updatepassword;
import com.bridgelabz.fundoonotes.dto.UserDto;
import com.bridgelabz.fundoonotes.dto.UserInformation;
import com.bridgelabz.fundoonotes.exceptions.UserExceptions;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utilis.JMSoperations;
import com.bridgelabz.fundoonotes.utilis.JWToperations;

@Service
@PropertySource("classpath:message.properties")

public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private JWToperations ope;
	@Autowired
	private JMSoperations ope1;
	@Autowired
	private Environment env;

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserServiceImp.class);

	@Transactional
	@Override
	public User register(UserDto userdto) throws IOException {
		User user = new User();
		if (userrepository.getUserByMail(userdto.getEmail()).isPresent()) {
			throw new UserExceptions(404, env.getProperty("exists"));
		}
		BeanUtils.copyProperties(userdto, user);
		BCryptPasswordEncoder by = new BCryptPasswordEncoder();
		user.setPassword(by.encode(user.getPassword()));
		user.setCreatedate(LocalDateTime.now());
		user.setUpdatedate(LocalDateTime.now());
		userrepository.saveUser(user);
		String email = user.getEmail();
		log.info("The user has successfully logged in " + user);
		String str = env.getProperty("url") + ope.JWTToken(user.getId());
		ope1.sendEmail(user.getEmail(), env.getProperty("Verifyemail"), str);
		return user;

	}

	@Transactional
	@Override
	public User userLogin(UserInformation userinformation) {
		User user = userrepository.getUserByMail(userinformation.getEmail())
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("notexist")));
		BCryptPasswordEncoder by = new BCryptPasswordEncoder();
		if (user.getVerify()== true && by.matches(userinformation.getPassword(), user.getPassword())==true) {
			return user;
		}
		throw new UserExceptions(404, env.getProperty("incorrect"));
	}

	@Transactional
	@Override
	public User verifyUser(String token) {
		Long id = ope.parseJWT(token);
		User user = userrepository.getUserById(id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		user.setVerify(true);
		userrepository.saveUser(user);
		return user;
	}

	@Transactional
	@Override
	public User setNewPassword(Updatepassword updatepassword, String token) {
		BCryptPasswordEncoder by = new BCryptPasswordEncoder();
		User user = userrepository.getUserById(ope.parseJWT(token))
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		if (updatepassword.getSetpassword().equals(updatepassword.getConfirmpassword())) {
			user.setPassword(by.encode(updatepassword.getConfirmpassword()));
			user.setUpdatedate(LocalDateTime.now());
			userrepository.saveUser(user);
			return user;
		}
		throw new UserExceptions(404, env.getProperty("incorrect"));
	}

}
