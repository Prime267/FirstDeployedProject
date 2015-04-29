package lviv.home.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;



@Entity
@Table(name = "book_table")
public class Book {

	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private Long bookId;

	@Column(name = "the_name")
	private String theName;

	@Column(name = "year_perform")
	private String date;

	@Column(name = "price")
	private Double price;
	@Column(name = "count")
	private Integer count;

	// @GeneratedValue
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id")
	private Author author;

	// @GeneratedValue
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genre_id")
	private Genre genre;

	public Book() {

	}

	

	public Book(String theName, LocalDate date, Double price, Integer count, Author author,
			Genre genre) {
		String datePerform = yeartoStr(date);
		
		this.theName = theName;
		this.date = datePerform;
		this.price = price;
		this.count = count;
		this.author = author;
		this.genre = genre;
	}



	public Book(String theName, LocalDate date, Double price) {
		String datePerform = yeartoStr(date);
		this.theName = theName;
//		this.count=count;
		this.date = datePerform;
		this.price = price;
	}

	public Book(String theName, Double price,Integer count) {
		this.theName = theName;
		this.date = "1970";
		this.price = price;
		this.count = count;
	}

	private static String yeartoStr(LocalDate date) {
		Integer dateInt = date.getYear();
		String dateStr = ""+dateInt;
		return dateStr;
	}
	

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTheName() {
		return theName;
	}

	public void setTheName(String theName) {
		this.theName = theName;
	}

	public String getDatePerform() {
		return this.date;
	}

	public void setDatePerform(String date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	
	
	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", theName=" + theName + ", datePerform="
				+ date + ", price="	+ price + ", author=" + author + ", genre=" + genre + "]";
	}

}
