package lviv.home.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lviv.home.dao.BookDAOHib;
import lviv.home.model.Author;
import lviv.home.model.Book;
import lviv.home.service2.AuthorService;
import lviv.home.service2.BookService;

@Controller
public class BookController {
	@Autowired
	@Qualifier("bookService")
	private BookDAOHib bookDAO;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String listBooks(Model model) {
		Book book = new Book();
//		author.setId(22);
		model.addAttribute("book",book);
		model.addAttribute("books",bookDAO.getAllBooks());
		return "books";
	}
	@RequestMapping(value = "/books/add", method = RequestMethod.POST)
	public String addBook(@ModelAttribute ("book") Book b, Integer authorId, Integer genreId) throws Exception {
		bookDAO.addBook(b, authorId,genreId);
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/books/remove", method = RequestMethod.POST)
	public String removeBook(Integer id) {
		bookDAO.deleteBook(id);
		return "redirect:/books";
	}
	
	@RequestMapping(value = "/books/edit", method = RequestMethod.POST)
	public String editBook(Integer id,Book book) {
		bookDAO.editBook(id, book);
		return "redirect:/books";
	}
	
}
