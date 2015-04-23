package lviv.home;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import lviv.home.model.Author;
import lviv.home.model.Book;

public class Helper {
	
//	public static void savePlayerMethod(Book book){
//
//		book.setAuthor(author);
//		book.setGenre(genre);
//		book.setUserId(getSavedUserInfo());
//		
//		
//		Session session = SessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		session.save(userInfo);
//		transaction.committ();
//		}
//
//		
//	private  Author getAuthorEntity() {
//	
//		Author author = new Author();
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		session.save(author);
//		
//		return null;
//	}
//	
//	public UserInfoEntity getSavedUserInfo(){
//		UserInfoEntity userInfo = new UserInfoEntity();
//		Session session = SessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
//		session.save(userInfo);
//		transaction.committ();
//		}
}
