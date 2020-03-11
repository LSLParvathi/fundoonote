package com.bridgelabz.fundoonotes.DTO;

public class UpdateNote {

	private String colours;
	private String title;
	private String description;

	public String getColours() {
		return colours;
	}

	public void setColours(String colours) {
		this.colours = colours;
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

	public UpdateNote(String colours, String title, String description) {
		this.colours = colours;
		this.title = title;
		this.description = description;
	}

}
