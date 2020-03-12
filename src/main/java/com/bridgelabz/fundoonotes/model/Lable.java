package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
 @Data
 @AllArgsConstructor 
 @NoArgsConstructor
 @Entity
 @Table(name = "lable")
 public class Lable {
 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Long id; 
 	@NotNull
 	private String title;
 	@NotNull
 	private String description;
 	@NotNull
 	private LocalDateTime createdate; 
 	@NotNull
 	private LocalDateTime updatedate;
 }
