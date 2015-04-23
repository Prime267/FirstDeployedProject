package lviv.home.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="author_table")
public class Author {
	@Id
	@GeneratedValue
	@Column(name="author_id")
	private Long authorId;
	
	@Column(name="author_name")
	private String authorName;
	
	@Column(name="country")
	private String country;
	
	
	@OneToMany(mappedBy="author")
	private List<Book> books = new ArrayList<Book>();
	
	
	public Author() {
		this.authorName = "default author";
	}
	
	public Author(String author_name, String country) {
		this.authorName = author_name;
		this.country=country;
	}

	

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [author_id=" + authorId + ", author_name="
				+ authorName + "]";
	}
	
	
	
}
