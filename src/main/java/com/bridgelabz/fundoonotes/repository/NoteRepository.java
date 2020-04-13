package com.bridgelabz.fundoonotes.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonotes.model.Note;
import com.bridgelabz.fundoonotes.utilis.Constant;

@Repository
public class NoteRepository {

	private RestHighLevelClient client;
	private ObjectMapper objectMapper;
	@Autowired
	private EntityManager entitymanager;

	public void saveNote(Note note) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.save(note);
	}

	public List<Note> getAllNotes() {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query query = currentsession.createQuery("from Note");
		List<Note> note = query.getResultList();
		return note;
	}

	public Optional<Note> getbyId(Long note_id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from Note where  note_id=:note_id").setParameter("note_id", note_id)
				.uniqueResultOptional();
	}

	public Optional<Note> getNoteByTitle(String title) {
		Session currentsession = entitymanager.unwrap(Session.class);
		return currentsession.createQuery("from Note where  title=:title").setParameter("title", title)
				.uniqueResultOptional();
	}

	public void deletenote(Note note) {
		Session currentsession = entitymanager.unwrap(Session.class);
		currentsession.delete(note);

	}

	public Note getnotebyId(Long note_id) {
		Session currentsession = entitymanager.unwrap(Session.class);
		Query query = currentsession.createQuery("from Note where  note_id=:note_id");
		query.setParameter("note_id", note_id);
		Note note = (Note) query.getResultList();
		return note;
	}

	public List<Note> searchNoteByTitleAndDescription(String text) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices(Constant.INDEX);
		searchRequest.types(Constant.TYPE);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		QueryBuilder query = QueryBuilders.boolQuery()
				.should(QueryBuilders.queryStringQuery(text).lenient(true).field("title").field("description"))
				.should(QueryBuilders.queryStringQuery("*" + text + "*").lenient(true).field("title")
						.field("description"));
		searchSourceBuilder.query(query);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (List<Note>)searchResponse;
	}

	/*
	 * private List<Note> getSearchResult(SearchResponse searchResponse) {
	 * SearchHit[] searchHits = searchResponse.getHits().getHits(); List<Note> note
	 * = new ArrayList<Note>(); if (searchHits.length > 0) {
	 * Arrays.stream(searchHits) .forEach(hit -> note.add(((Object)
	 * objectMapper).convertValue(hit.getSourceAsMap(), Note.class))); } return
	 * note; }
	 */

	private SearchRequest buildSearchRequest(String index, String type) {

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices(index);
		searchRequest.types(type);

		return searchRequest;
	}

}
