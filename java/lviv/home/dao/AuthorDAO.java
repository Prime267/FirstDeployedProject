package lviv.home.dao;

import java.util.List;

import lviv.home.HibernateUtil;
import lviv.home.model.Author;
import lviv.home.model.Book;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AuthorDAO {

	public static void seveAuthor(Author author) {
		Session session = HibernateUtil.startTransaction();
		session.save(author);
		HibernateUtil.finishTransaction(session);
	}

	public static Author getAuthor(Long id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Author author = (Author) session.get(Author.class, id);
		session.close();

		return author;
	}

	public static List<Author> getAllAuthors() {
		Session session = HibernateUtil.startTransaction();
		List<Author> authors = session.createQuery("select a from Author a").list();
		HibernateUtil.finishTransaction(session);
		return authors;
	}

	public static List<Author> getAuthorsByCountry(String country){
		Session session = HibernateUtil.startTransaction();
		List<Author> authors = (List<Author>)session.createQuery("select a from Author a where a.country=:country").setParameter("country", country).list();
		HibernateUtil.finishTransaction(session);
		return authors;
	}
	
	
	
	
	public static void removeAuthor(Integer id) {
		Session session = HibernateUtil.startTransaction();
		session.createQuery("delete from Author where authorId='" + id + "'").executeUpdate();
		HibernateUtil.finishTransaction(session);
	}

	public static void editAuthor(Integer id, Author author) {
		Session session = HibernateUtil.startTransaction();
		session.createSQLQuery(
				"UPDATE `authordatabase`.`author_table` SET `author_name`='"
						+ author.getAuthorName() + "', `country`='" + author.getCountry()
						+ "' WHERE `author_id`='5';").executeUpdate();
		HibernateUtil.finishTransaction(session);
	}

//	public static void refreshAuthors() {
//
//		Session session = HibernateUtil.startTransaction();
//		List<Author> authors = session.createQuery("SELECT a FROM Author a").list();
//		if (authors.size() == 0) {
//			session.createSQLQuery("alter table authordatabase.author_table auto_increment=0;")
//					.executeUpdate();// обнулення автоінк
//		} else {
//			session.createSQLQuery("delete from authordatabase.author_table").executeUpdate();// очистка
//			// таблиці
//			session.createSQLQuery("alter table authordatabase.author_table auto_increment=0;")
//					.executeUpdate();// обнулення автоінк
//		}
//
//		HibernateUtil.finishTransaction(session);
//
//		for (Author author : authors) { // обнулення всіх book_id
//			author.setAuthorId(null);
//		}
//		session = HibernateUtil.startTransaction();
//		for (Author author : authors) {// збереження книжок з обнуленими IDшками
//			session.save(author);
//		}
//		HibernateUtil.finishTransaction(session);
//	}

}
