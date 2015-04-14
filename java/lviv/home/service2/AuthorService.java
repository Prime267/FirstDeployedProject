package lviv.home.service2;

import lviv.home.model.Author;

public interface AuthorService {
	
	
	public void saveAuthor(Author Author);
	public Author getAuthor(Integer id);
	public Iterable<Author> getAllAuthors();
	public void deleteAuthor(Integer id);
	public Author editAuthor(Integer id, Author author);
}
