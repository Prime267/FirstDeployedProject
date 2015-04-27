package lviv.home.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import org.springframework.data.convert.JodaTimeConverters.LocalDateToDateConverter;

@Entity
@Table(name = "book_table")
public class Book {

	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private Long bookId;

	@Column(name = "the_name")
	private String theName;

	@Column(name = "date_perform")
	private Date date;

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

	public Book(Long bookId, String theName, LocalDate date, Double price, Author author,Genre genre) {
		Date datePerform = localDatetoDate(date);
		this.theName = theName;
		this.date = datePerform;
		this.price = price;
		this.count = 0;
		this.author = author;
		this.genre = genre;
	}

	public Book(String theName, LocalDate date, Double price) {
		Date datePerform = localDatetoDate(date);
		this.theName = theName;
		this.count=0;
		this.date = datePerform;
		this.price = price;
	}

	public Book(String theName, Double price, Integer count) {
		this.theName = theName;
		this.price = price;
//		this.count = count;
	}

	private static Date localDatetoDate(LocalDate date) {
		Date datePerform = new Date();
		int month = date.getMonthValue() - 1;
		int day = date.getDayOfMonth();
		if (date.getYear() < 1900) {
			datePerform = new Date(-(1900 - date.getYear()), month, day);
		} else {
			datePerform = new Date(date.getYear() - 1900, month, day);
		}
		return datePerform;
	}
	
	private LocalDate dateToLocalDate(Date date) {
		int year = 0;
		int month = date.getMonth()+1;
		int day = date.getDate();
		if (date.getYear()>=0) {
			year = 1900+ date.getYear();
		} else{
			year = 1900 - date.getYear();
		}
		LocalDate localdate = LocalDate.of(year, month, day);
		return localdate;
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

	public LocalDate getDatePerform() {
		LocalDate pDate = dateToLocalDate(date);
		return pDate;
	}

	public void setDatePerform(LocalDate date) {
		Date datePerform = localDatetoDate(date);
		this.date = datePerform;
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
				+ date.getDate() + "-" + date.getMonth() + "-" + date.getYear() + ", price="
				+ price + ", author=" + author + ", genre=" + genre + "]";
	}

}
