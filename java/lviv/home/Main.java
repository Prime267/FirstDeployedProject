package lviv.home;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import javax.print.DocFlavor.URL;

import org.springframework.context.ApplicationContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lviv.home.dao.AuthorDAOHib;
import lviv.home.dao.BookDAOHib;
import lviv.home.model.Author;
import lviv.home.model.Book;
import lviv.home.model.Genre;
import lviv.home.service.impl.AuthorServiceImpl;
import lviv.home.service.impl.BookServiceImpl;

public class Main {
	public static void main(String[] args) {

		// URL resourceUrl = JUnitRunner.class.getResource("root-context.xml")

		// ApplicationContext ac = new
		// ClassPathXmlApplicationContext("root-context.xml");
		//
		// AuthorServiceImpl asi = ac.getBean(AuthorServiceImpl.class,
		// "authorService");
		// BookServiceImpl bsi = ac.getBean(BookServiceImpl.class,
		// "bookService");

//		 Author author1 = new Author("Іван Франко", "Україна");
		// Author author2 = new Author("Еріх Марія Ремарк", "Німеччина");
		// Author author3 = new Author("Стівен Кінг", "США");

		BookDAOHib.refreshBooks();
	}
}
