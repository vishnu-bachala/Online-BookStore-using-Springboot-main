package com.example.demo.Controller;

import java.util.List;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Publisher;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.CategoryNotFound;
import com.example.demo.ExceptionHandling.PublisherNotFound;
import com.example.demo.Service.BookService;
import com.example.demo.Service.OrderItemService;
import com.example.demo.Service.Impl.BookImpl;
import com.example.demo.Service.Impl.CategoryImpl;
import com.example.demo.Service.Impl.OrderItemImpl;
import com.example.demo.Service.Impl.PublisherImpl;

@RestController
public class BookController {
	
	@Autowired
	BookImpl bookImpl;
	
	@Autowired
	CategoryImpl categoryImpl;
	
	@Autowired
	PublisherImpl publisherImpl;
	
	@Autowired
	OrderItemImpl orderItemImpl;
	
	@GetMapping("/books")
	public List<Book> findAllBooks() {
		return bookImpl.findAllBooks();
	}

	@PostMapping("/books")
	public ResponseEntity<Object> addBook(@RequestBody @Valid Book b) throws CategoryNotFound, PublisherNotFound {
		
		Book b1 = bookImpl.addBook(b);
		b1.setCategory(b.getCategory());
		b1.setPublisher(b.getPublisher());
		bookImpl.addBook(b1);
		return new ResponseEntity<>(b1, HttpStatus.CREATED);
	}

	@GetMapping("/books/{id}")
	public Book FindBookByBookid(@PathVariable int id) throws BookNotFound {
		return bookImpl.findBookById(id);
	}

	@GetMapping("/book/{title}")
	public List<Book> FindBookByTitle(@PathVariable String title) throws BookNotFound{
		return bookImpl.findBookbyTitle(title);
	}

	@DeleteMapping("/books/{id}")
	public String deleteBook(@PathVariable int id) throws BookNotFound {
		
			bookImpl.deleteBook(id);
			return "Book deleted Successfully.";
	}
	@PutMapping("/books/{bid}")
	public Book updateBook(@PathVariable int bid,@RequestBody @Valid Book b) throws CategoryNotFound, PublisherNotFound, BookNotFound
	{
		Category c=categoryImpl.findCategoryById(b.getBookcategoryid());
		Publisher p=publisherImpl.findPublisherById(b.getBookpublisherid());
		
		return bookImpl.updateBook(bid, b);
		
	}


}
