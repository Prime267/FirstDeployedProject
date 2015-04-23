	package lviv.home;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
	private static  SessionFactory sessionFactory  = createNewSessionFactory();
	private static ServiceRegistry serviceRegistry;
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	
	private static SessionFactory createNewSessionFactory(){
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;		
	}
	
	public static Session startTransaction() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		return session;
	}
	public static void finishTransaction(Session session) {
		session.getTransaction().commit();
		session.close();
	}
}

