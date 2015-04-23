package lviv.home.service2;

import lviv.home.model.Book;

public interface BookService {

	
	public void saveBook(Book book);
	public Book getBook(Integer id);
	public Iterable<Book> getAllBooks();
	public void deleteBook(Integer id);
//	public Book editBook(Integer id,Book book);
	}
