package lviv.home.controller;

import java.util.List;

import lviv.home.dao.AuthorDAO;
import lviv.home.model.Author;
import lviv.home.model.Book;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorController {
	
	private AuthorDAO authorDao = new AuthorDAO();
	
	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public String listAuthor(Model model) {
		Author author = new Author();
//		author.setAuthorName("¬вед≥ть ≥м*€");
//		author.setCountry("¬вед≥ть крањну");
		model.addAttribute("author", author);
		model.addAttribute("authors", authorDao.getAllAuthors());
		return "authors";
	}
	
	@RequestMapping(value = "/authors/add", method = RequestMethod.POST)
	public String addBook(@ModelAttribute ("author") Author author) {
		authorDao.seveAuthor(author);
		return "redirect:/authors";
	}
	
	
//	public String buyBook(Integer id) {
//		
//		bookDao.
//		return "redirect:/books";
//	}
	
	
	@RequestMapping(value = "/authors/remove", method = RequestMethod.POST)
	public String removeAuthor(Integer id) {
		authorDao.removeAuthor(id);
		return "redirect:/authors";
	}
	
	@RequestMapping(value = "/authors/edit", method = RequestMethod.POST)
	public String editBook(Integer id,Author author) {
		authorDao.editAuthor(id, author);
		return "redirect:/authors";
	}
	
	@RequestMapping(value = "/authors/refresh", method = RequestMethod.POST)
	public String refreshAuthors() {
		authorDao.refreshAuthors();;
		return "redirect:/authors";
	}
	
	
}
