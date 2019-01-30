package kr.ac.huh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.huh.model.Book;
import kr.ac.huh.service.BookService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping
	public String adminPage() {
		return "admin";
	}
	
	@RequestMapping("/bookInventory")
	public String getBooks(Model model) { // controller -> model -> view
		
		List<Book> books = bookService.getBooks();
		model.addAttribute("books", books);
		
		return "bookInventory";
	}
}
