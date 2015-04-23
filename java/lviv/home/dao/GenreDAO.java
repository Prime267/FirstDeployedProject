package lviv.home.dao;

import lviv.home.model.Genre;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GenreDAO extends CrudRepository<Genre, Integer>{

}
