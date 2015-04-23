package lviv.home.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lviv.home.dao.AuthorDAO;
import lviv.home.model.Author;
import lviv.home.service2.AuthorService;
@Service("authorService")
public class AuthorServiceImpl implements AuthorService{
	@Autowired
	private AuthorDAO dao;

	@Override
	public void saveAuthor(Author author) {
		dao.save(author);
	}

	@Override
	public Author getAuthor(Integer id) {
		return dao.findOne(id);
	}

	@Override
	public Iterable<Author> getAllAuthors() {
		return dao.findAll();
	}

	@Override
	public void deleteAuthor(Integer id) {
		dao.delete(id);		
	}

	@Override
	public Author editAuthor(Integer id, Author author) {
		Author author1 = dao.findOne(id);
		author1.setAuthorName(author.getAuthorName());
		return dao.save(author1);
	}
}
