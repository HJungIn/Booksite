package kr.ac.huh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.huh.model.Book;
import kr.ac.huh.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping("/books")
	public String getProducts(Model model) {
		
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		
		return "books"; 
	}
	

}
