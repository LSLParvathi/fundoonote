package com.bridgelabz.fundoonotes.dto;

import org.springframework.stereotype.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class SearchNote {

	private String title;
	private String description;

}
