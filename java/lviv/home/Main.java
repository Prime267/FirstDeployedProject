package lviv.home;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.DocFlavor.URL;
import javax.swing.text.StyledEditorKit.BoldAction;

import org.springframework.context.ApplicationContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransientObjectException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lviv.home.controller.AuthorController;
import lviv.home.controller.BookController;
import lviv.home.dao.AuthorDAO;
import lviv.home.dao.BookDAO;
import lviv.home.model.Author;
import lviv.home.model.Book;
import lviv.home.model.Genre;

public class Main {
	public static void main(String[] args) throws Exception {

		// ApplicationContext ac = new
		// ClassPathXmlApplicationContext("root-context.xml");
		//
		// AuthorController aCont = ac.getBean(AuthorController.class,
		// "AuthorController");
//
//
		Genre genre1 = new Genre("������� ����");
		Genre genre2 = new Genre("������� ����");
		
//		ArrayList<Book> books1 = new ArrayList<Book>();
//		ArrayList<Book> books2 = new ArrayList<Book>();
		
		Author author1 = new Author("�'�", "�����");
		Author author2 = new Author("�'�", "�����");
		
		Book book1 = new Book("Test book 1", LocalDate.of(2015, 5, 13), 10.0, 1, author1, genre1);
		Book book2 = new Book("Test book 2", LocalDate.of(2015, 5, 13), 10.0, 3, author1, genre1);
		Book book3 = new Book("Test book 3", LocalDate.of(2015, 5, 13), 10.0, 3, author1, genre1);
		
		
		
//		BookDAO.refreshBooks();
//		BookDAO.addBook(book1);
//		BookDAO.addBook(book2);
//		BookDAO.addBook(book3);
		System.out.println(AuthorDAO.getAuthorsByCountry("ͳ�������"));
		
//		BookDAO.buyTheBook(book1);
//		Book book = new Book("New book", LocalDate.now(), 10.0, 1, author, genre);

//		BookDAO.addBook(book);

		
		// BookDAOHib.deleteAllBooks();
		// BookDAOHib.refreshBooks();
		// BookDAOHib.addBook(book, 1, 1);
		// BookDAOHib.editBook(1, newBook);

	}
}
