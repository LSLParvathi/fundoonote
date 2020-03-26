package com.bridgelabz.fundoonotes.DTO;

import org.springframework.stereotype.Indexed;

@Indexed
public class SearchNote {
 
	private String title; 
	private String description;

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

/*
 * @OneToMany(fetch = FetchType.LAZY)
 * 
 * @IndexColumn(name = "list_position")
 * 
 * @Cascade(org.hibernate.annotations.CascadeType.ALL)
 * 
 * @IndexedEmbedded
 */
/*
 * @Entity
 * 
 * @Indexed public class ProductCatalog {
 * 
 * @Id
 * 
 * @GeneratedValue
 * 
 * @DocumentId public Long getCatalogId() {...}
 * 
 * @Field public String getTitle() {...}
 * 
 * @Field public String getDescription() {...}
 * 
 * @OneToMany(fetch = FetchType.LAZY)
 * 
 * @IndexColumn(name = "list_position")
 * 
 * @Cascade(org.hibernate.annotations.CascadeType.ALL)
 * 
 * @IndexedEmbedded private List<Item> getItems() {...}
 * 
 * // ... }
 */