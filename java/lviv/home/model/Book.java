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
	private Integer bookId;

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

	public Book(String theName, LocalDate date, Double price, Integer count, Author author, Genre genre) {
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
		// this.count=count;
		this.date = datePerform;
		this.price = price;
	}

	public Book(String theName, Double price, Integer count) {
		this.theName = theName;
		this.date = "1970";
		this.price = price;
		this.count = count;
	}

	private static String yeartoStr(LocalDate date) {
		Integer dateInt = date.getYear();
		String dateStr = "" + dateInt;
		return dateStr;
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
		return this.date;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	// @Enumerated(EnumType.ORDINAL)
	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public boolean equals(Object obj) {
		Book book = (Book) obj;
		
		System.out.println("book.getAuthor() in Book.equals: "+book.getAuthor());
		System.out.println("this in Book.equals: "+this.getAuthor());
//		System.out.println("this.getAuthor().equals(book.getAuthor())==="+this.getAuthor().equals(book.getAuthor()));
		if (this.getTheName().equals(book.getTheName()) && this.getPrice().equals(book.getPrice())
				&& this.getDate().equals(book.getDate()) &&this.getGenre().equals(book.getGenre())
				&&this.getAuthor().equals(book.getAuthor())) {
			return true;
		} else {
			return false;
		}
	}

	
	
	public boolean specialEqualsWithoutAuthorAndGenre(Object obj) {
		Book book = (Book) obj;
		System.out.println("book.getAuthor() in Book.equals: "+book.getAuthor());
		System.out.println("this in Book.equals: "+this.getAuthor());
//		System.out.println("this.getAuthor().equals(book.getAuthor())==="+this.getAuthor().equals(book.getAuthor()));
		if (this.getTheName().equals(book.getTheName()) && this.getPrice().equals(book.getPrice())
				&& this.getDate().equals(book.getDate())) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", theName=" + theName + ", datePerform=" + date + ", price=" + price
				+ ", author=" + author + ", genre=" + genre + "]";
	}

}
