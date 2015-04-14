package lviv.home.service.impl;

import lviv.home.dao.BookDAO;
import lviv.home.model.Book;
import lviv.home.service2.BookService;

public class BookServiceImpl implements BookService{

		private BookDAO dao;

		@Override
		public void saveBook(Book book) {
			dao.save(book);
			
		}

		@Override
		public Book getBook(Integer id) {
			return dao.findOne(id);
		}

		@Override
		public Iterable<Book> getAllBooks() {
			return dao.findAll();
		}

		@Override
		public void deleteBook(Integer id) {
			dao.delete(id);	
		}

		@Override
		public Book editBook(Integer id, Book book) {
			Book book1 = dao.findOne(id);
			book1.setTheName(book.getTheName());
			book1.setPrice(book.getPrice());
			return dao.save(book1);
		}
}
