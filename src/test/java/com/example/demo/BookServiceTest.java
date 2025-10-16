package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Publisher;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.CategoryNotFound;
import com.example.demo.ExceptionHandling.PublisherNotFound;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Service.BookService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceTest {
	@Autowired
	BookService bookService;
	
	@MockBean
	BookRepository bdao;
	private Category category;
	private Publisher publisher;
	private Book book,book1;
	
	@BeforeEach
	void init() {
		category=category.builder().category_id(1).name("Coding").build();
		publisher=publisher.builder().publisherId(1).name("Anudip")
				.contactNumber("9347273515").address("Mulund West ,Mumbai").build();
		
		book = book.builder().bookId(1).title("Linux").author("Saurabh").bookcategoryid(1).publicationDate(LocalDate.now())
				.price(250).description("Programming with Linux").bookpublisherid(1).quantityAvailable(50).category(category)
				.publisher(publisher).build();
		book1 = book.builder().bookId(2).title("C Programming").author("Vilas").bookcategoryid(1).publicationDate(LocalDate.now())
				.price(250).description("Programming with C by Vilas Mahajan").bookpublisherid(1).quantityAvailable(50).category(category)
				.publisher(publisher).build();
		
		}
	@Test
	void testAddBook() throws CategoryNotFound, PublisherNotFound {
		Mockito.when(bdao.save(book)).thenReturn(book);
		assertEquals(book, bookService.addBook(book));
}
	@Test
	void testGetBook()throws BookNotFound {
		Optional<Book> b1 = Optional.of(book);

		Mockito.when(bdao.findById(1)).thenReturn(b1);

		assertEquals("Linux", bookService.findBookById(1).getTitle());	
	}

	@Test
	void testGetAll() {
		List<Book> books1 = Stream.of(book, book1).collect(Collectors.toList());
		Mockito.when(bdao.findAll()).thenReturn(books1);

		assertEquals(2, bookService.findAllBooks().size());
	}

	@Test
	void testDeleteBook() throws BookNotFound {

		bookService.deleteBook(2);
		Mockito.verify(bdao).deleteById(2);

	}
}