package lviv.home.model;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "author_table")
public class Genre {
	@Id
	@GeneratedValue
	@Column(name ="genre_id")
	private Integer genreId;
	
	@Column(name ="genre_name")
	private String genreName;
	
	private Set<Book> books;
	
	
	public Genre() {
		this.genreName = "default genre";
	}

	public Genre(String genreName) {
			this.genreName = genreName;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Genre [genreId=" + genreId + ", genreName=" + genreName + "]";
	} 
	
	
	
}
