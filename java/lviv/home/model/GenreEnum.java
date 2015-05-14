package lviv.home.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre_table")
public enum GenreEnum {
		
	ÄÎÊÓÌÅÍÒÀË²ÇÌ("Äîêóìåíòàë³çì"),ÍÀÓÊÎÂÀ_ÔÀÍÒÀÑÒÈÊÀ("Íàóêîâà ôàíòàñòèêà"),ÔÅÍÒÅÇ²;

		@Id
		@GeneratedValue
		@Column(name ="genre_id")
		private Integer genreId;
		
		@Column(name ="genre_name")
		private String genreName;
		
		@OneToMany(mappedBy="genre")
		private List<Book> books = new ArrayList<Book>();
		
		
	private GenreEnum() {
		}

	private GenreEnum(String genre) {
		this.genreName = genre;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenre(String genre) {
		this.genreName = genre;
	}
	
	

}
