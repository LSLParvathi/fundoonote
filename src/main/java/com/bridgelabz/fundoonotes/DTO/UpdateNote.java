package com.bridgelabz.fundoonotes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNote {

	private String colours;
	private String title;
	private String description;

}
