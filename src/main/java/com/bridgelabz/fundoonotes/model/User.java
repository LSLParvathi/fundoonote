package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "userdetails")
public class User {

	/*
	 * private Object user; private boolean verified = true; private int n = 200;
	 * 
	 * public User(Object user, int n, boolean verified) {
	 * 
	 * this.user = user; this.verified = verified; this.n = n; }
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@NotNull(message="firstname is required")
	private String firstname;
	@NotNull(message = "lastname is required")
	private String lastname;
	@NotNull(message = "mobilenumber is required")
	@Size(min=10, max=10)
	private String mobilenumber;
	@Email(message = "email is required")
	private String email;
	@NotNull(message = "password is required") 
	private String password;

	private Boolean verify = false;

	private LocalDateTime createdate;

	private LocalDateTime updatedate;

	public Boolean getVerify() {
		return verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 
	public LocalDateTime getCreatedate() {
		return createdate;
	}

	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}

	public LocalDateTime getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(LocalDateTime updatedate) {
		this.updatedate = updatedate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", mobilenumber="
				+ mobilenumber + ", email=" + email + ", password=" + password + ", verify=" + verify + ", createdate="
				+ createdate + ", updatedate=" + updatedate + ", note=" + note + "]";
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<Note> note;

	public List<Note> getNote() {
		return note;
	}

	public void setNote(List<Note> note) {
		this.note = note;
	}

	 

}
