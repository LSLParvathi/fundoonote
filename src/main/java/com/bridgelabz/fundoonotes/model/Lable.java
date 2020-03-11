package com.bridgelabz.fundoonotes.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GeneratorType;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
 
 @Data
 @AllArgsConstructor 
 @NoArgsConstructor
 @Entity
 @Table(name = "lable")
 public class Lable {
 	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Long id; 
 	private String title; 
 	private String description;
 	private LocalDateTime createdate; 
 	private LocalDateTime updatedate;
 
 	 
 }
