
package com.bridgelabz.fundoonotes.service;

import java.io.IOException;
import java.util.List;

import com.bridgelabz.fundoonotes.model.Note;

public interface ElasticSearchService {

	public String createNote(Note note) throws IOException;

	public Note findById(String id) throws Exception;

	public String upDateNote(Note note) throws Exception;

	public String deleteNote(String id) throws IOException;

	List<Note> getNoteByTitleAndDescription(String text);

}
