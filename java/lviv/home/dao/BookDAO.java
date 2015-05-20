package lviv.home.dao;

import java.time.LocalDate;
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
		Book book = (Book) session.get(Book.class, new Long(bookId));
		session.close();

		return book;
	}

	public List<Book> getAllBooks() {

		Session session = HibernateUtil.startTransaction();
		List<Book> allBooks = (List<Book>) session.createQuery("select b from Book b").list();
		HibernateUtil.finishTransaction(session);
		return allBooks;
	}

	@SuppressWarnings("unchecked")
	public static void addBook(Book newBook) throws Exception {
		int bookId = 0;
		int countBooks = newBook.getCount();
		Session session = HibernateUtil.startTransaction();// ������� ����������

		List<Book> allBooks = (List<Book>) session.createQuery("select b from Book b").list();// ������
																							// ��
																							// �����

		if (allBooks.contains(newBook)) { // ���� � ����
				System.out.println("First equals");
			
				for (Book book : allBooks) {
					if (book.equals(newBook)) {
						countBooks = book.getCount() + newBook.getCount();//����� �������
						bookId = book.getBookId(); // ������ ID �����
					}
				}
			session.createSQLQuery(
					"UPDATE `authordatabase`.`book_table` SET `count`='" + countBooks + "' WHERE `book_id`='" + bookId
							+ "';").executeUpdate();// ���������� �������

		} else {
			List authorsId = session.createQuery("select authorId from Author a where a.authorName=:authorName")
					.setParameter("authorName", newBook.getAuthor().getAuthorName()).list();

			List genresId = session.createQuery("select genreId from Genre g where g.genreName=:genreName")
					.setParameter("genreName", newBook.getGenre().getGenreName()).list();

			if (authorsId.size() == 0) {// ���� ���� - �����
				session.save(newBook.getAuthor());
			}
			if (genresId.size() == 0) {// ���� ���� - �����
				session.save(newBook.getGenre());
			}
			session.save(newBook);// �� ������� -- ����� ���������� �����
			// --------------------------------------------------------------------------------------------------------------
			try {
				HibernateUtil.finishTransaction(session);
			} catch (Exception e) {
				session = HibernateUtil.startTransaction();
				allBooks = (List<Book>) session.createQuery("select b from Book b").list();// ������
				// ��
				// �����
				bookId = 0;
				for (Book book : allBooks) {
					if (newBook.specialEqualsWithoutAuthorAndGenre(book)) {
						bookId = book.getBookId();
						System.out.println("In method ADD!!");
						System.out.println(newBook);
						System.out.println(book);
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
			session.createSQLQuery("alter table authordatabase.book_table auto_increment=0;").executeUpdate();// ���������
																												// ������
		} else {
			session.createSQLQuery("delete from authordatabase.book_table").executeUpdate();// �������
			// �������
			session.createSQLQuery("alter table authordatabase.book_table auto_increment=0;").executeUpdate();// ���������
																												// ������
		}

		HibernateUtil.finishTransaction(session);

		for (Book book : books) { // ��������� ��� book_id
			book.setBookId(null);
		}
		session = HibernateUtil.startTransaction();
		for (Book book : books) {// ���������� ������ � ���������� ID�����
			session.save(book);
		}
		HibernateUtil.finishTransaction(session);
	}

	public static void deleteBook(Integer bookId) {
		Session session = HibernateUtil.startTransaction();
		session.createQuery("delete Book where bookId='" + bookId + "'").executeUpdate();// �������
																							// �������
		HibernateUtil.finishTransaction(session);

	}

	public static void deleteAllBooks() {
		Session session = HibernateUtil.startTransaction();
		session.createSQLQuery("delete from authordatabase.book_table").executeUpdate();// �������
																						// �������
		HibernateUtil.finishTransaction(session);
	}

	public static void saveBook(Book book) {
		Session session = HibernateUtil.startTransaction();
		session.save(book);
		HibernateUtil.finishTransaction(session);
	}

	public static void buyTheBook(Book buyBook) {
		BoughtBook boughtBook = new BoughtBook(buyBook);// ����������
		boughtBook.setSaleDate(LocalDate.now());// ������������ ���� ���������
		int count = boughtBook.getCount();

		Session session = HibernateUtil.startTransaction();

		List<Book> books = (List<Book>) session.createQuery("select b from Book b").list();// ������
		// ��
		// �����
		for (Book book : books) {
			if (book.equals(buyBook)) {
				Book bookAfterBought = book;
				if (book.getCount() - boughtBook.getCount() < 0) {
					throw new IndexOutOfBoundsException("���� ������ ��������� ���������� � ��������");
				}
				if (book.getCount() - boughtBook.getCount() == 0) {
					session.delete(book);
				} else {
					count = book.getCount() - count;
					int id = book.getBookId();
					session.createSQLQuery("UPDATE `authordatabase`.`book_table` SET `count`='" + count
							+ "' WHERE `book_id`='" + id + "';");
				}

				// ______________________________________________________________________________________________
				int countBooks = boughtBook.getCount();
				List<BoughtBook> boughtBooks = (List<BoughtBook>) session.createQuery("select b from BoughtBook b")
						.list();// ������
				// �� ������ �����
				// �����

				if (books.contains(boughtBook)) { // ���� � ����

					countBooks = books.get(0).getCount() + boughtBook.getCount();// �����
																					// �������
					int bookId = books.get(0).getBookId(); // ������ ID �����
					session.createSQLQuery(
							"UPDATE `authordatabase`.`sold_books_table` SET `count`='" + countBooks
									+ "' WHERE `book_id`='" + bookId + "';").executeUpdate();// ����������
																								// �������

				} else {
					List authorsId = session
							.createQuery("select authorId from Author a where a.authorName=:authorName")
							.setParameter("authorName", boughtBook.getAuthor().getAuthorName()).list();

					List genresId = session.createQuery("select genreId from Genre g where g.genreName=:genreName")
							.setParameter("genreName", boughtBook.getGenre().getGenreName()).list();

					if (authorsId.size() == 0) {// ���� ���� - �����
						session.save(boughtBook.getAuthor());
					}
					if (genresId.size() == 0) {// ���� ���� - �����
						session.save(boughtBook.getGenre());
					}
					session.save(boughtBook);// �� ������� -- ����� ����������
												// �����
					// ______________________________________________________________________________________________
				}
			}
			HibernateUtil.finishTransaction(session);
		}
	}
}