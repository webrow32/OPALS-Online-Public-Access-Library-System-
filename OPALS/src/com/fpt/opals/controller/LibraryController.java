package com.fpt.opals.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fpt.opals.model.Book;
import com.fpt.opals.services.BookService;

@RestController
public class LibraryController {

	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/book", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> listAllBooks() {
		List<Book> books = bookService.findAllBooks();
		if(books.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		System.out.println("Fetching book with id "+id);
		Book book = bookService.findById(id);
		
		if(book == null) {
			System.out.println("Book with id "+id+" not found.");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<Void> saveBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
		System.out.println("Saving new book.. "+ book.getTitle());

		if(!bookService.isBookIsbnUnique(book.getId(), book.getIsbnCode())) {
			System.out.println("The Book with ISBN "+book.getIsbnCode() + " already exist.");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		bookService.saveBook(book);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
		System.out.println("Updating book.." + id);
		
		Book currentBook = bookService.findById(id);
		
		if(currentBook == null) {
			System.out.println("Book with id "+id+" not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}

		bookService.updateBook(currentBook);
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}
	
	@RequestMapping(value = {"/book/{isbnCode}"}, method = {RequestMethod.DELETE})
	public ResponseEntity<Book> deleteBook(@PathVariable("isbnCode") String isbnCode) {
		System.out.println("Deleting Book with isbn "+isbnCode);
	
		Book book = bookService.findBookByIsbn(isbnCode);
		if(book == null) {
			System.out.println("Unable to delete. Book with isbn "+isbnCode+" not found");
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	
		bookService.deleteBookByIsbn(isbnCode);
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
	
}
