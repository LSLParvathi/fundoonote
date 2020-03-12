package com.bridgelabz.fundoonotes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class NoteDto  {

	private String title;
	private String description;

}
