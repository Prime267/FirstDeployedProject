package lviv.home.dao;

import java.util.List;

import lviv.home.HibernateUtil;
import lviv.home.model.Author;
import lviv.home.model.Book;
import lviv.home.model.Genre;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOHib {

	public static Book getBook(Integer bookId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Book book = (Book) session.get(Book.class, new Long(bookId));
		session.close();

		return book;
	}

	public List<Book> getAllBooks() {

		Session session = HibernateUtil.startTransaction();
		List<Book> allBooks = (List<Book>) session.createQuery("select b from Book b");
		HibernateUtil.finishTransaction(session);
		return allBooks;
	}

	@SuppressWarnings("unchecked")
	public static void addBook(Book newBook, Integer authorId, Integer genreId) throws Exception {

		Session session = HibernateUtil.startTransaction();

		List<Book> book = (List<Book>) session.createQuery(
				"select b from Book b where b.theName='" + newBook.getTheName()
						+ "' and b.date='"// шукаємо подібну книгу
						+ newBook.getDatePerform() + "'" + "and b.price='" + newBook.getPrice()	+ "'").list();

		Integer countBooks = newBook.getCount();

		if (book.size() != 0) { // якщо знайшли подібну
			countBooks = book.get(0).getCount() + newBook.getCount();
			Long n = book.get(0).getBookId(); // дістаємо ID книги
			session.createSQLQuery(
					"UPDATE `authordatabase`.`book_table` SET `count`='" + countBooks
							+ "' WHERE `book_id`='" + n + "';").executeUpdate();

		} else {
			newBook.setAuthor((Author) session.get(Author.class, authorId));
			newBook.setGenre((Genre) session.get(Genre.class, genreId));
			session.save(newBook);
			
			HibernateUtil.finishTransaction(session);
		}

	}

	public static void editBook(Integer bookId, Book book) {
		Session session = HibernateUtil.startTransaction();

		session.createSQLQuery("UPDATE `authordatabase`.`book_table` SET `the_name`='"
				+ book.getTheName() + "', `year_perform`='" + book.getDatePerform()
				+ "', `price`='" + book.getPrice() + "', `count`='" + book.getCount()
				+ "', `author_id`='" + 1 + "', `genre_id`='" + 1
				+ "' WHERE `book_id`='"+bookId+"';").executeUpdate();

		HibernateUtil.finishTransaction(session);

	}

	@SuppressWarnings("unchecked")
	public static void refreshBooks() {

		Session session = HibernateUtil.startTransaction();
		List<Book> books = session.createQuery("SELECT b FROM Book b").list();
		if (books.size() == 0) {
			session.createSQLQuery("alter table authordatabase.book_table auto_increment=0;")
					.executeUpdate();// обнулення автоінк
		}

		session.createSQLQuery("delete from authordatabase.book_table").executeUpdate();// очистка
																						// таблиці
		session.createSQLQuery("alter table authordatabase.book_table auto_increment=0;")
				.executeUpdate();// обнулення автоінк
		HibernateUtil.finishTransaction(session);

		for (Book book : books) { // обнулення всіх book_id
			book.setBookId(null);
		}
		session = HibernateUtil.startTransaction();
		for (Book book : books) {// збереження книжок з обнуленими IDшками
			session.save(book);
		}
		HibernateUtil.finishTransaction(session);
	}

	public static void deleteBook(Integer bookId) {
		Session session = HibernateUtil.startTransaction();
		session.createQuery("delete Book where bookId='" + bookId + "'").executeUpdate();// очистка
																							// таблиці
		HibernateUtil.finishTransaction(session);

	}

	public static void deleteAllBooks() {
		Session session = HibernateUtil.startTransaction();
		session.createSQLQuery("delete from authordatabase.book_table").executeUpdate();// очистка
																						// таблиці
		HibernateUtil.finishTransaction(session);
	}

	public static void saveBook(Book book) {
		Session session = HibernateUtil.startTransaction();
		session.save(book);
		HibernateUtil.finishTransaction(session);
	}

}
