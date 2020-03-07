package com.bridgelabz.fundoonotes.DTO;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/*
 * @Component
 * 
 * @Entity
 * 
 * @Table(name = "trashtable") public class TrashTable {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; private
 * Long note_id; private boolean is_archive; private boolean is_pin; private
 * boolean is_trash; private String colours; private LocalDateTime remindme;
 * private String title; private String description;
 * 
 * public Long getid() { return id; }
 * 
 * public void setid(Long id) { this.id = id; }
 * 
 * public Long getNote_id() { return note_id; }
 * 
 * public void setNote_id(Long note_id) { this.note_id = note_id; }
 * 
 * public boolean isIs_archive() { return is_archive; }
 * 
 * public void setIs_archive(boolean is_archive) { this.is_archive = is_archive;
 * }
 * 
 * public boolean isIs_pin() { return is_pin; }
 * 
 * public void setIs_pin(boolean is_pin) { this.is_pin = is_pin; }
 * 
 * public boolean isIs_trash() { return is_trash; }
 * 
 * public void setIs_trash(boolean is_trash) { this.is_trash = is_trash; }
 * 
 * public String getColours() { return colours; }
 * 
 * public void setColours(String colours) { this.colours = colours; }
 * 
 * public LocalDateTime getRemindme() { return remindme; }
 * 
 * public void setRemindme(LocalDateTime remindme) { this.remindme = remindme; }
 * 
 * public String getTitle() { return title; }
 * 
 * public void setTitle(String title) { this.title = title; }
 * 
 * public String getDescription() { return description; }
 * 
 * public void setDescription(String description) { this.description =
 * description; }
 * 
 * public TrashTable(Long note_id, boolean is_archive, boolean is_pin, boolean
 * is_trash, String colours, LocalDateTime remindme, String title, String
 * description) {
 * 
 * this.note_id = note_id; this.is_archive = is_archive; this.is_pin = is_pin;
 * this.is_trash = is_trash; this.colours = colours; this.remindme = remindme;
 * this.title = title; this.description = description; }
 * 
 * }
 */