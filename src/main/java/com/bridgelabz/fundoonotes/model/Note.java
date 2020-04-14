package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "noteTable")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, scope = Note.class)

public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long note_id;
	private boolean archive = false;
	private boolean pin;
	private boolean trash;
	private String colours;
	private LocalDateTime remindme;
	private String title;
	private String description;

	@ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	private List<Lable> lable;

	public List<Lable> getLable() {
		return lable;
	}

	public void setLable(List<Lable> lable) {
		this.lable = lable;
	}

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private List<User> collaborator;

	public List<User> getCollaborator() {
		return collaborator;
	}

	public void setCollaborator(List<User> collaborator) {
		this.collaborator = collaborator;
	}

}
