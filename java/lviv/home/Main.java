package lviv.home;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
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
	public static void main(String[] args) throws Exception {

		// URL resourceUrl = JUnitRunner.class.getResource("root-context.xml")

		// ApplicationContext ac = new
		// ClassPathXmlApplicationContext("root-context.xml");
		//
		// AuthorServiceImpl asi = ac.getBean(AuthorServiceImpl.class,
		// "authorService");
		// BookServiceImpl bsi = ac.getBean(BookServiceImpl.class,
		// "bookService");
		Genre genre1 = new Genre("genre");
		
		Author author1 = new Author("Іван Франко", "Україна");
		
		
		// Author author2 = new Author("Еріх Марія Ремарк", "Німеччина");
		// Author author3 = new Author("Стівен Кінг", "США");

		Book book = new Book("book", 100.0, 1);
		Book newBook = new Book("sfsd", LocalDate.now(), 1.0, 50, author1,genre1 );

		
		BookDAOHib.editBook(1, newBook);
		
//		BookDAOHib.deleteAllBooks();
//		BookDAOHib.refreshBooks();
//		BookDAOHib.addBook(book, 1, 1);
//		BookDAOHib.editBook(1, newBook);
		
		
//		Session session = HibernateUtil.startTransaction();
//
////		session.save(genre1);
////		session.save(author1);
//		newBook.setAuthor((Author) session.get(Author.class, 1));
//		newBook.setGenre((Genre) session.get(Genre.class, 18));
//		session.save(newBook);
//		HibernateUtil.finishTransaction(session);
//		System.out.println(id.size());
//
//		
//		Integer year = LocalDate.of(2015, 1, 1).getYear();
//		String yearStr = ""+year;
//		System.out.println(yearStr);
		// BookDAOHib.addBook(book, 1, 1);

		// BookDAOHib.editBook(1, book);

	}
}
