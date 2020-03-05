package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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
	private Long id;

	private String firstname;

	private String lastname;

	private String mobilenumber;

	private String email;

	private String password;

	private Boolean verify = false;

	private LocalDateTime date;

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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", mobilenumber="
				+ mobilenumber + ", email=" + email + ", password=" + password + ", verify=" + verify + ", date=" + date
				+ "]";
	}

	@OneToMany(cascade = CascadeType.ALL )
	@JoinColumn(name = "id")
	private List<Note> note;

	public List<Note> getNote() {
		return note;
	}

	public void setNote(List<Note> note) {
		this.note = note;
	}

}
