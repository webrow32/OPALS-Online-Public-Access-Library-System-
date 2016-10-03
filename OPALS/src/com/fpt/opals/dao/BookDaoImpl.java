package com.fpt.opals.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.fpt.opals.model.Book;

@Repository("bookDao")
public class BookDaoImpl extends AbstractDao<Integer, Book > implements BookDao {

	@Override
	public Book findById(int id) {
		return getByKey(id);
	}

	@Override
	public void saveBook(Book book) {
		persist(book);
	}

	@Override
	public void deleteBookByIsbn(String isbn) {
		Query query = getSession().createQuery("delete from Book where isbncode = :isbncode");
		query.setString("isbncode", isbn);
		query.executeUpdate();
	}

	

	@Override
	public Book findBookByIsbn(String isbn) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("isbnCode", isbn));
		return (Book) criteria.uniqueResult();
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findAllBooks() {
		Criteria criteria = createEntityCriteria();
		return (List<Book>) criteria.list();
	}

}
