package com.fpt.opals.dao;

import java.util.List;

import com.fpt.opals.model.Book;

public interface BookDao {

	Book findById(int id);
	
	void saveBook(Book book);
	
	void deleteBookByIsbn(String isbn);
	
	List<Book> findAllBooks();
	
	Book findBookByIsbn(String isbn);
	
}
