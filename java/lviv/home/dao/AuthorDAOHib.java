package lviv.home.dao;

import lviv.home.HibernateUtil;
import lviv.home.model.Author;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AuthorDAOHib {

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
	
	public static void removeAuthor(Long id) {
		Session session = HibernateUtil.startTransaction();
		session.delete("Author", session.get(Author.class, id));
		HibernateUtil.finishTransaction(session);
		
		}

}
