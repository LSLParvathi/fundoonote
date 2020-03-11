package com.bridgelabz.fundoonotes.DTO;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Entity 
@Table(name = "trashtable")
public class TrashTable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private boolean archive;
	private boolean pin;
	private boolean trash;
	private String colours;
	private LocalDateTime remindme;
	private String title;
	private String description;
}

