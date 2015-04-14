package lviv.home.model;


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
	
	@Column(name="price")
	private Double price;
	
	
	@Column(name="author")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable=false, name="author_id")
	private Author author;

//	private Genre genre;
	
	
	public Book() {
		super();
		this.theName = "default theName";
		this.price = 10.0;
		this.author = new Author();
	}
	public Book( String theName, Double price, Author author) {
		super();
		this.theName = theName;
		this.price = price;
		this.author = author;
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

	
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", theName=" + theName + ", price="
				+ price + ", author=" + author + "]";
	}
	
	
	

	
	
}
