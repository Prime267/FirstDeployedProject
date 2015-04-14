package lviv.home.dao;

import lviv.home.model.Book;

import org.springframework.data.repository.CrudRepository;

public interface BookDAO extends CrudRepository<Book, Integer>{

}
