package com.fpt.opals.services;

import java.util.List;

import com.fpt.opals.model.Book;

public interface BookService {

	Book findById(int id);
	
	Book findBookByIsbn(String isbn);
	
	void saveBook(Book book);
	
	void updateBook(Book book);
	
	void deleteBookByIsbn(String isbn);
	
	List<Book> findAllBooks();
	
	boolean isBookIsbnUnique(Integer id, String isbn);
	
}


