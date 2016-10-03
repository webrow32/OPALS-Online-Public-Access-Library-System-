package com.fpt.opals.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotEmpty
	@Column(name = "isbnCode", unique=true,  nullable = false)
	private String isbnCode;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "authorName", nullable = false)
	private String authorName;

	@Column(name = "publisher", nullable = false)
	private String publisher;

	@Column(name = "yearPublished", nullable = false)
	private String yearPublished;

	public Book() {
		
	}
	
	public Book(int id, String isbnCode, String title, String description,
			String category, String authorName, String publisher,
			String yearPublished) {
		super();
		this.id = id;
		this.isbnCode = isbnCode;
		this.title = title;
		this.description = description;
		this.category = category;
		this.authorName = authorName;
		this.publisher = publisher;
		this.yearPublished = yearPublished;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbnCode() {
		return isbnCode;
	}

	public void setIsbnCode(String isbnCode) {
		this.isbnCode = isbnCode;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYearPublished() {
		return yearPublished;
	}

	public void setYearPublished(String yearPublished) {
		this.yearPublished = yearPublished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id ^ (id >>> 32));;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbnCode=" + isbnCode + ", title=" + title
				+ ", description=" + description +", category=" + category + ", authorName=" + authorName
				+ ", publisher=" + publisher + ", yearPublished="
				+ yearPublished + "]";
	}

}
