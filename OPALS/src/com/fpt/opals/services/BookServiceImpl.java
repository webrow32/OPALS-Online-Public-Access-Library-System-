package com.fpt.opals.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.opals.dao.BookDao;
import com.fpt.opals.model.Book;


@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	public Book findById(int id) {
		return bookDao.findById(id);
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		return bookDao.findBookByIsbn(isbn);
		
	}

	@Override
	public void saveBook(Book book) {
		bookDao.saveBook(book);
	}

	@Override
	public void updateBook(Book book) {
		Book entity = bookDao.findById(book.getId());
		if(entity != null) {
			
			entity.setIsbnCode(book.getIsbnCode());
			entity.setTitle(book.getTitle());
			entity.setDescription(book.getDescription());
			entity.setCategory(book.getCategory());
			entity.setAuthorName(book.getAuthorName());
			entity.setPublisher(book.getPublisher());
			entity.setYearPublished(book.getYearPublished());
		}
	}

	@Override
	public void deleteBookByIsbn(String isbn) {
		bookDao.deleteBookByIsbn(isbn);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookDao.findAllBooks();
	}
	
	@Override
	public boolean isBookIsbnUnique(Integer id, String isbn) {
		Book book = findBookByIsbn(isbn);
		return (book == null || (id != null) && (book.getId() == id));
		
	}

}
