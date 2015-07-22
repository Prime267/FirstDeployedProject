package lviv.home.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lviv.home.HibernateUtil;
import lviv.home.model.Author;
import lviv.home.model.Book;
import lviv.home.model.BoughtBook;
import lviv.home.model.Genre;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransientObjectException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("bookService")
public class BookDAO {

	public static Book getBook(Integer bookId) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Book book = (Book) session.get(Book.class, bookId);
		session.close();
		return book;
	}

	public List<Book> getAllBooks() {

		Session session = HibernateUtil.startTransaction();
		List<Book> allBooks = (List<Book>) session.createQuery("select b from Book b").list();
		HibernateUtil.finishTransaction(session);
		return allBooks;
	}

	// -----------------------------------------------------------FILTERS--------------------------------------------------------------
	public static List<Book> getBooksByName(String name) {

		Session session = HibernateUtil.startTransaction();
		List<Book> books = (List<Book>) session.createQuery("select b from Book b where b.theName=:name")
				.setParameter("name", name).list();
		HibernateUtil.finishTransaction(session);

		return books;
	}

	public static List<Book> getBooksByAuthor(Author author) {
		int id = 0;
		Session session = HibernateUtil.startTransaction();
		List<Author> authors = (List<Author>) session.createQuery("select a from Author a").list();
		for (Author authorFromList : authors) {
			if (author.equals(authorFromList)) {
				id = authorFromList.getAuthorId();
			}
		}
		List<Book> books = (List<Book>) session.createQuery("select b from Book b where b.author.authorId=:id")
				.setParameter("id", id).list();
		HibernateUtil.finishTransaction(session);
		return books;
	}

	public static List<Book> getBooksByYear(String year) {
		Session session = HibernateUtil.startTransaction();
		List<Book> books = (List<Book>) session.createQuery("select b from Book b where b.date=:year")
				.setParameter("year", year).list();
		HibernateUtil.finishTransaction(session);
		return books;
	}

	public static List<Book> getBooksByCountry(String country) {
		Session session = HibernateUtil.startTransaction();
		List<Book> books = (List<Book>) session.createQuery("select b from Book b where b.author.country=:country")
				.setParameter("country", country).list();
		HibernateUtil.finishTransaction(session);
		return books;
	}

	public static List<Book> getBooksByPrise(int price) {
		Session session = HibernateUtil.startTransaction();
		double priceD = (double) price;
		List<Book> books = (List<Book>) session.createQuery("select b from Book b where b.price=:priceD")
				.setParameter("priceD", priceD).list();
		HibernateUtil.finishTransaction(session);
		return books;
	}

	public static List<Book> getBooksByGenre(Genre genre) {
		int id = 0;
		Session session = HibernateUtil.startTransaction();
		List<Genre> genres = (List<Genre>) session.createQuery("select g from Genre g").list();
		for (Genre genreFromList : genres) {
			if (genre.equals(genreFromList)) {
				id = genreFromList.getGenreId();
			}
		}
		List<Book> books = (List<Book>) session.createQuery("select b from Book b where b.genre.genreId=:id")
				.setParameter("id", id).list();
		HibernateUtil.finishTransaction(session);
		return books;
	}

	// --------------------------------------------------------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public static void addBook(Book newBook) throws Exception {
		int bookId = 0;
		int countBooks = newBook.getCount();
		Session session = HibernateUtil.startTransaction();// початок транзакції

		List<Book> allBooks = (List<Book>) session.createQuery("select b from Book b").list();// дістаємо
																								// всі
																								// книги

		if (allBooks.contains(newBook)) { // якщо є така
			System.out.println("First equals");

			for (Book book : allBooks) {
				if (book.equals(newBook)) {
					countBooks = book.getCount() + newBook.getCount();// додаю
																		// кількість
					bookId = book.getBookId(); // дістаємо ID книги
				}
			}
			session.createSQLQuery(
					"UPDATE `authordatabase`.`book_table` SET `count`='" + countBooks + "' WHERE `book_id`='" + bookId
							+ "';").executeUpdate();// встановлюю кількість

		} else {
			List authorsId = session.createQuery("select authorId from Author a where a.authorName=:authorName")
					.setParameter("authorName", newBook.getAuthor().getAuthorName()).list();

			List genresId = session.createQuery("select genreId from Genre g where g.genreName=:genreName")
					.setParameter("genreName", newBook.getGenre().getGenreName()).list();

			if (authorsId.size() == 0) {// якщо немає - додаю
				session.save(newBook.getAuthor());
			}
			if (genresId.size() == 0) {// якщо немає - додаю
				session.save(newBook.getGenre());
			}
			session.save(newBook);// не знайшли -- додаю новесеньку книгу

			try {
				HibernateUtil.finishTransaction(session);
			} catch (Exception e) {
				session = HibernateUtil.startTransaction();
				allBooks = (List<Book>) session.createQuery("select b from Book b").list();// дістаємо
				// всі
				// книги
				bookId = 0;
				for (Book book : allBooks) {
					if (newBook.specialEqualsWithoutAuthorAndGenre(book)) {
						bookId = book.getBookId();
//						System.out.println("In method ADD!!");
//						System.out.println(newBook);
//						System.out.println(book);
					}
				}

				String query = "UPDATE `authordatabase`.`book_table` SET `author_id`='" + authorsId.get(0)
						+ "', `genre_id`='" + genresId.get(0) + "' WHERE `book_id`='" + bookId + "';";

				session.createSQLQuery(query).executeUpdate();
				HibernateUtil.finishTransaction(session);
			}
		}

	}

	public static void editBook(Integer bookId, Book book) {
		Session session = HibernateUtil.startTransaction();

		session.createSQLQuery(
				"UPDATE `authordatabase`.`book_table` SET `the_name`='" + book.getTheName() + "', `year_perform`='"
						+ book.getDate() + "', `price`='" + book.getPrice() + "', `count`='" + book.getCount()
						+ "', `author_id`='" + 1 + "', `genre_id`='" + 1 + "' WHERE `book_id`='" + bookId + "';")
				.executeUpdate();

		HibernateUtil.finishTransaction(session);

	}

	@SuppressWarnings("unchecked")
	public static void refreshBooks() {

		Session session = HibernateUtil.startTransaction();
		List<Book> books = session.createQuery("SELECT b FROM Book b").list();
		if (books.size() == 0) {
			session.createSQLQuery("alter table authordatabase.book_table auto_increment=0;").executeUpdate();// обнулення
																												// автоінк
		} else {
			session.createSQLQuery("delete from authordatabase.book_table").executeUpdate();// очистка
			// таблиці
			session.createSQLQuery("alter table authordatabase.book_table auto_increment=0;").executeUpdate();// обнулення
																												// автоінк
		}

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

	public static void buyBook(Integer id, Integer amount) {
		Book buyBook = getBook(id);
		BoughtBook boughtBook = new BoughtBook(buyBook);// конвертуємо
		boughtBook.setCount(amount);// кількість потрібних екземплярів
		boughtBook.setSaleDate(LocalDateTime.now());// встановлюємо дату
													// придбання
		int count = boughtBook.getCount();

		Session session = HibernateUtil.startTransaction();

		List<Book> books = (List<Book>) session.createQuery("select b from Book b").list();// дістаємо
		// всі
		// книги
		for (Book book : books) {
			if (book.equals(buyBook)) {
				Book bookAfterBought = book;
				if (book.getCount() - boughtBook.getCount() < 0) {
					throw new IndexOutOfBoundsException("Немає стільки доступних екземплярів в наявності");
				}
				if (book.getCount() - boughtBook.getCount() == 0) {
					session.delete(book);
				} else {
					count = book.getCount() - count;
					id = book.getBookId();
					session.createSQLQuery(
							"UPDATE `authordatabase`.`book_table` SET `count`='" + count + "' WHERE `book_id`='" + id
									+ "';").executeUpdate();
				}

				// ______________________________________________________________________________________________
				int countBooks = boughtBook.getCount();
				List<BoughtBook> boughtBooks = (List<BoughtBook>) session.createQuery("select b from BoughtBook b")
						.list();// дістаємо
				// всі продані книги
				// книги

				if (books.contains(boughtBook)) { // якщо є така

					countBooks = books.get(0).getCount() + boughtBook.getCount();// додаю
																					// кількість
					int bookId = books.get(0).getBookId(); // дістаємо ID книги
					session.createSQLQuery(
							"UPDATE `authordatabase`.`sold_books_table` SET `count`='" + countBooks
									+ "' WHERE `book_id`='" + bookId + "';").executeUpdate();// встановлюю
																								// кількість

				} else {
					List authorsId = session
							.createQuery("select authorId from Author a where a.authorName=:authorName")
							.setParameter("authorName", boughtBook.getAuthor().getAuthorName()).list();

					List genresId = session.createQuery("select genreId from Genre g where g.genreName=:genreName")
							.setParameter("genreName", boughtBook.getGenre().getGenreName()).list();

					if (authorsId.size() == 0) {// якщо немає - додаю
						session.save(boughtBook.getAuthor());
					}
					if (genresId.size() == 0) {// якщо немає - додаю
						session.save(boughtBook.getGenre());
					}
					session.save(boughtBook);// не знайшли -- додаю новесеньку
												// книгу
				}
			}
			HibernateUtil.finishTransaction(session);
		}
	}
}