package lviv.home.dao;

import java.math.BigInteger;
import java.util.List;

import lviv.home.HibernateUtil;
import lviv.home.model.Book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAOHib {

	public static Book getBook(Long bookId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Book getsBook = (Book) session.get(Book.class, bookId);
		session.close();
		sessionFactory.close();

		return getsBook;
	}

	public static void addBook(Book book, Long authorId, Long genreId) {
		Session session = HibernateUtil.startTransaction();
		session.save(book);
		List listId = (List) session.createSQLQuery(
				"select book_id from `authordatabase`.`book_table` where the_name='"
						+ book.getTheName() + "';").list();
		BigInteger n = (BigInteger) listId.get(0);
		session.createSQLQuery(
				"UPDATE `authordatabase`.`book_table` SET `author_id`='" + authorId
						+ "', `genre_id`='" + genreId + "' WHERE `book_id`='" + n + "';")
				.executeUpdate();

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

	public static void deleteAllBooks() {
		Session session = HibernateUtil.startTransaction();
		session.createSQLQuery("delete from authordatabase.book_table").executeUpdate();// очистка
																						// таблиці
		HibernateUtil.finishTransaction(session);
	}

	public static void addBookTwo(Book newBook, Integer amount, Long authorId, Long genreId)
			throws Exception {

		Session session = HibernateUtil.startTransaction();

		List<Book> book = (List<Book>) session.createQuery(
				"select b from Book b where b.theName='" + newBook.getTheName()
						+ "' and b.date='"// шукаємо подібну книгу
						+ newBook.getDatePerform() + "'" + "and b.price='" + newBook.getPrice()
						+ "'").list();
		
		Integer countBooks = amount;
		
		if (book.size() != 0) { // якщо знайшли подібну
			countBooks = book.get(0).getCount() + amount;
			Long n = book.get(0).getBookId(); // дістаємо ID книги
			session.createSQLQuery(
					"UPDATE `authordatabase`.`book_table` SET `count`='" + countBooks
							+ "' WHERE `book_id`='" + n + "';").executeUpdate();

		} else {
			session.save(newBook);
			List id = (List) session.createQuery(
					"select b.bookId from Book b where b.theName='" + newBook.getTheName()
							+ // дістаємо ID книги яку тільки додали
							"' and b.date='" + newBook.getDatePerform() + "'" + "and b.price='"
							+ newBook.getPrice() + "'").list();

			Long n = (Long) id.get(0);
			session.createSQLQuery(
					"UPDATE `authordatabase`.`book_table` SET `count`='" + countBooks
							+ "',`author_id`='" + authorId + "', `genre_id`='" + genreId
							+ "' WHERE `book_id`='" + n + "';").executeUpdate();
		}
		HibernateUtil.finishTransaction(session);
	}

}
