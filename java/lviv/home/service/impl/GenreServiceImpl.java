package lviv.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import lviv.home.dao.GenreDAO;
import lviv.home.model.Genre;
import lviv.home.service2.GenreService;

public class GenreServiceImpl implements GenreService{
	@Autowired
	private GenreDAO	dao;

	@Override
	public void saveGenre(Genre genre) {
		dao.save(genre);
	}

	@Override
	public Genre getGenre(Integer id) {
		return dao.findOne(id);
	}

	@Override
	public Iterable<Genre> getAllGenres() {
		return dao.findAll();
	}

	@Override
	public void deleteGenre(Integer id) {
		dao.delete(id);		
	}

	@Override
	public Genre editBook(Integer id, Genre genre) {
		Genre newGenre = dao.findOne(id);
		newGenre.setGenreName(genre.getGenreName());
		return dao.save(newGenre);
	}
}
