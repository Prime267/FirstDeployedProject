package lviv.home.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="sold_books_table")
public class BoughtBook {
	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private Integer bookId;

	@Column(name = "the_name")
	private String theName;

	@Column(name = "year_perform")
	private String date;

	@Column(name = "price")
	private Double price;

	@Column(name = "count")
	private Integer count;
	
	@Column(name = "sale_date")
	private String saleDate;
	// @GeneratedValue
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author_id")
	private Author author;

	// @GeneratedValue
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genre_id")
	private Genre genre;

	
	
	
	
	public BoughtBook(Book book) {
		this.theName = book.getTheName();
		this.date = book.getDate();
		this.price = book.getPrice();
		this.count = book.getCount();
		this.saleDate = null;
		this.author = book.getAuthor();
		this.genre = book.getGenre();
	}

	

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTheName() {
		return theName;
	}

	public void setTheName(String theName) {
		this.theName = theName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate.toString();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
