package lviv.home.service2;

import lviv.home.model.Genre;

public interface GenreService {
	public void saveGenre(Genre genre);
	public Genre getGenre(Integer id);
	public Iterable<Genre> getAllGenres();
	public void deleteGenre(Integer id);
	public Genre editBook(Integer id,Genre genre);
}
