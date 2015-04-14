package lviv.home.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import lviv.home.model.Author;

@Repository
public interface AuthorDAO extends CrudRepository<Author, Integer>{

}
