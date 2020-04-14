package com.bridgelabz.fundoonotes.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgelabz.fundoonotes.DTO.Updatepassword;
import com.bridgelabz.fundoonotes.DTO.UserDTO;
import com.bridgelabz.fundoonotes.DTO.UserInformation;
import com.bridgelabz.fundoonotes.DTO.updateInformation;
import com.bridgelabz.fundoonotes.Exceptions.UserExceptions;
import com.bridgelabz.fundoonotes.model.User;
import com.bridgelabz.fundoonotes.repository.UserRepository;
import com.bridgelabz.fundoonotes.utilis.JMSoperations;
import com.bridgelabz.fundoonotes.utilis.JWToperations;
import com.bridgelabz.fundoonotes.utilis.RabbitMqSender;

@Service
public class UserServiceImp implements UserService {

	private RabbitMqSender  rabbitmqsender;
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
	public User register(UserDTO userdto) throws IOException {

		User user = new User();
		BeanUtils.copyProperties(userdto, user);
		boolean n = userrepository.IfEmailExists(user.getEmail()).isPresent();
		if (n) {
			throw new UserExceptions(404, env.getProperty("exists"));
		}
		BCryptPasswordEncoder by = new BCryptPasswordEncoder();
		String newpwd = by.encode(user.getPassword());
		user.setPassword(newpwd);
		user.setCreatedate(LocalDateTime.now());
		user.setUpdatedate(LocalDateTime.now());
		user.setVerify(false);
		userrepository.save(user);
		String email = user.getEmail();
		log.info("The user has successfully logged in " + user);
		String str = env.getProperty("url") + ope.JWTToken(user.getId());
		rabbitmqsender.send(email);
		rabbitmqsender.Reciver(email);
		ope1.sendEmail(user.getEmail(), env.getProperty("Verifyemail"), str);
		return user;

	}

	@Transactional
	@Override
	public String userlogin(UserInformation userinformation) {
		User user = userrepository.IfEmailExists(userinformation.getEmail())
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("notexist")));
		Long id = user.getId();
		String token = ope.JWTToken(id);
		String password = userinformation.getPassword();
		String newpassword = user.getPassword();
		BCryptPasswordEncoder by = new BCryptPasswordEncoder();
		boolean c = by.matches(password, newpassword);
		if (c) {
			return token;
		}
		throw new UserExceptions(404, env.getProperty("incorrect"));
	}

	@Transactional
	@Override
	public List<User> getall() {
		List<User> user = userrepository.get();
		if (user == null) {
			throw new UserExceptions(404, env.getProperty("nodata"));
		}
		return user;
	}

	@Override
	public User getUserById(Long id) {
		User user = userrepository.get(id).orElseThrow(() -> new UserExceptions(404, env.getProperty("nodata")));
		return user;
	}

	@Transactional
	@Override
	public User verifyUser(String token) {
		Long id = ope.parseJWT(token);
		User user = getUserById(id);
		user.setVerify(true);
		userrepository.saveUser(user);
		return user;
	}

	@Transactional
	@Override
	public User updateuser(updateInformation updateinformation, Long id) {
		User user = getUserById(id);
		BCryptPasswordEncoder by = new BCryptPasswordEncoder();
		String newpwd = by.encode(updateinformation.getPassword());
		user.setPassword(newpwd);
		user.setMobilenumber(updateinformation.getPhonenumber());
		user.setUpdatedate(LocalDateTime.now());
		userrepository.saveUser(user);
		return user;
	}

	@Transactional
	@Override
	public void deleteUser(Long id) {
		User user = getUserById(id);
		userrepository.deleteUser(user);

	}

	@Transactional
	@Override
	public User setnewpassword(Updatepassword updatepassword) {
		String s1 = updatepassword.getConfirmpassword();
		String s2 = updatepassword.getSetpassword();
		String mail = updatepassword.getMail();
		User user = getUserByMail(mail);
		if (s2.equals(s1)) {
			BCryptPasswordEncoder by = new BCryptPasswordEncoder();
			String newpwd = by.encode(s1);
			user.setPassword(newpwd);
			user.setUpdatedate(LocalDateTime.now());
			userrepository.saveUser(user);
			return user;
		}
		throw new UserExceptions(404, env.getProperty("incorrect"));
	}

	@Transactional
	@Override
	public User getUserByMail(String mail) {
		User user = userrepository.getUserByMail(mail)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("notexist")));
		return user;
	}

	@Override
	public User getImageUrl(String token) {
		Long id = ope.parseJWT(token); 
		User url = userrepository.findUserByProfile(id)
				.orElseThrow(() -> new UserExceptions(404, env.getProperty("notexist")));
		return url;
	}
	
	
	

}
