package lviv.home.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "genre_table")
public class Genre {
	@Id
	@GeneratedValue
	@Column(name ="genre_id")
	private Integer genreId;
	
	@Column(name ="genre_name")
	private String genreName;
	

	@OneToMany(mappedBy="genre")
	private List<Book> books = new ArrayList<Book>();
	
	
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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public boolean equals(Object obj) {
		Genre genre = (Genre)obj;
		if (this.getGenreName().equals(genre.getGenreName())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.genreId+" "+genreName;
	} 
	
	
	
}
