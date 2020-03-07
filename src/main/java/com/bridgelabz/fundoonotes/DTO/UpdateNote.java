package com.bridgelabz.fundoonotes.DTO;

public class UpdateNote
{

	private String archive;
	private String colours;
	private String remindme; 
	private String title;
	private String description;
	public String getArchive() {
		return archive;
	}
	public void setArchive(String archive) {
		this.archive = archive;
	}
	public String getColours() {
		return colours;
	}
	public void setColours(String colours) {
		this.colours = colours;
	}
	public String getRemindme() {
		return remindme;
	}
	public void setRemindme(String remindme) {
		this.remindme = remindme;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateNote(String archive, String colours, String remindme, String title, String description) {
	 
		this.archive = archive;
		this.colours = colours;
		this.remindme = remindme;
		this.title = title;
		this.description = description;
	}
	
}
