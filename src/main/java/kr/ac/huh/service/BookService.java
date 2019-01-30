package kr.ac.huh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.huh.dao.BookDao;
import kr.ac.huh.model.Book;

@Service
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	public List<Book> getBooks(){
		return bookDao.getBooks();
	}
	
}
