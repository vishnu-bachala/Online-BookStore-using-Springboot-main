package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.CategoryNotFound;
import com.example.demo.ExceptionHandling.PublisherNotFound;



public interface BookService {

	public Book addBook(Book b) throws CategoryNotFound, PublisherNotFound;

	public List<Book> findAllBooks();

	public Book updateBook(int bid,Book b)throws BookNotFound, CategoryNotFound, PublisherNotFound;

	public void deleteBook(int bid)throws BookNotFound;

	public Book findBookById(int did)throws BookNotFound;

	public List<Book> findBookbyTitle(String title)throws BookNotFound;

//	public String addFeedback(int id,String feedback) throws BookNotFound;

}