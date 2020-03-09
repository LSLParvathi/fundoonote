package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@Entity
@Table(name = "noteTable")
public class Note {

 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long note_id;
	private boolean archive=false;
	private boolean pin;
	private boolean trash;
	private String colours;
	private LocalDateTime remindme;
	private String title;
	private String description;
	 
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Lable> lable;
	
	
}
