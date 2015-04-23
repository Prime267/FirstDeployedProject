package lviv.home.model;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name="book_table")
public class Book {
	
	@Id
	@GeneratedValue
	@Column(name="book_id")
	private Long bookId;
	
	@Column(name="the_name")
	private String theName;
	
//	@Column(name="date_perform")
//	private LocalDate datePerform;
//	
	@Column(name="price")
	private Double price;
	
	
//	@GeneratedValue
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="author_id")
	private Author author;

//	@GeneratedValue
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="genre_id")
	private Genre genre;


	
	
	
	
	
	
	public Book() {
	}
	public Book(String theName, Double price, Author author, Genre genre) {
		this.theName = theName;
//		this.datePerform = datePerform;
		this.price = price;
		this.author = author;
		this.genre = genre;
	}
	
	public Book(String theName, Double price) {
		this.theName = theName;
//		this.datePerform = datePerform;
		this.price = price;
		
		
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


//	public LocalDate getDatePerform() {
//		return datePerform;
//	}


//	public void setDatePerform(LocalDate datePerform) {
//		this.datePerform = datePerform;
//	}


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


	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", theName=" + theName
				+ ", datePerform="  + ", price=" + price
				+ ", author=" + author + ", genre=" + genre + "]";
	}

//	private Genre genre;
	

	
	
	

	
	
}
