package com.bridgelabz.fundoonotes.DTO;

public class NoteDto
{

	private String title;
	private String description;
	
	@Override
	public String toString() {
		return "NoteDto [title=" + title + ", description=" + description + "]";
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
	 
	
}
