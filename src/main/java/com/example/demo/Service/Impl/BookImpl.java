package com.example.demo.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Publisher;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.CategoryNotFound;
import com.example.demo.ExceptionHandling.PublisherNotFound;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.PublisherRepository;
import com.example.demo.Service.BookService;

@Service
public class BookImpl implements BookService{

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Override
	public Book addBook(Book b) throws CategoryNotFound, PublisherNotFound {
		// TODO Auto-generated method stub
		Optional<Category> op1 = categoryRepository.findById(b.getBookcategoryid());
		if (op1.isPresent()) {
		Category c=categoryRepository.findById(b.getBookcategoryid()).get();
		b.setCategory(c);
		Optional<Publisher> op2 = publisherRepository.findById(b.getBookpublisherid());
		if (op2.isPresent()) {
		
		Publisher p=publisherRepository.findById(b.getBookpublisherid()).get();
		
		b.setPublisher(p);	
		b.setUserratings(0.0);
		return bookRepository.save(b);}
		else {
			throw new PublisherNotFound("Publisher not found on this Id:"+b.getBookpublisherid());
		}
	}
		else {
			throw new CategoryNotFound("Category not found on this Id:"+b.getBookcategoryid());
		}
	}

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		
		return bookRepository.findAll();
	}

	@Override
	public Book updateBook(int bid,Book b1) throws BookNotFound, CategoryNotFound, PublisherNotFound {

		Optional<Book> op = bookRepository.findById(bid);
		if (op.isPresent()) {
			Book _book = bookRepository.findById(bid).get();

			Optional<Category> op1 = categoryRepository.findById(b1.getBookcategoryid());
			if (op1.isPresent()) {
				Category c=categoryRepository.findById(b1.getBookcategoryid()).get();
				_book.setBookcategoryid(b1.getBookcategoryid());

			_book.setCategory(c);
			}else {
				throw new CategoryNotFound("Category Not Found on this Id:"+b1.getBookcategoryid());
			}
			
			Optional<Publisher> op2 = publisherRepository.findById(b1.getBookpublisherid());
			if (op2.isPresent()) {
	
			Publisher p=publisherRepository.findById(b1.getBookpublisherid()).get();
			_book.setPublisher(p);
			
			_book.setBookpublisherid(b1.getBookpublisherid());
			}else
			{
				throw new PublisherNotFound("Publisher not Found on this Id:"+b1.getBookpublisherid());
			}
			_book.setTitle(b1.getTitle());
			_book.setAuthor(b1.getAuthor());	
			_book.setPrice(b1.getPrice());
			_book.setPublicationDate(b1.getPublicationDate());
			_book.setQuantityAvailable(b1.getQuantityAvailable());
			
			return bookRepository.save(_book);
		} else {
			System.out.println("Book record is not present in this id :"+bid);
		
			throw new BookNotFound("Book record is not present in this id :"+bid);
		}
		
			}

	@Override
	public void deleteBook(int bid) throws BookNotFound {
		// TODO Auto-generated method stub
		Optional<Book> op = bookRepository.findById(bid);
		if (op.isPresent())

		{
			bookRepository.deleteById(bid);

		} else {
			throw new BookNotFound("Book not present of this id:"+bid);
					}
	}	

	@Override
	public Book findBookById(int did) throws BookNotFound{
		Optional<Book> op = bookRepository.findById(did);
		if (op.isPresent())

		{
			return bookRepository.findById(did).get();

		} else {
			throw new BookNotFound("Book not present of this id:"+did);
		}
	}

	@Override
	public List<Book> findBookbyTitle(String title) throws BookNotFound{
	
		List<Book> op = bookRepository.findBookByTitle(title);
		if (op.isEmpty())

		{
			throw new BookNotFound("Books not present of this title: "+title);
			

		} else {
			return bookRepository.findBookByTitle(title);
		}
		}

//	@Override
//	public String addFeedback(int id,String feedback) throws BookNotFound {
//		Optional<Book> optional = bookRepository.findById(id);
//		if (optional.isPresent()) {
//			Book b1=bookRepository.findById(id).get();
//			List<String> allfeedbacks =new ArrayList();
//			allfeedbacks.addAll(b1.getFeedback());
//			allfeedbacks.add(feedback);
//			b1.setFeedback(allfeedbacks);
//			bookRepository.save(b1);
//			return "Your Feedback has been Submitted.";
//		}
//		else {
//			throw new BookNotFound("Book Not Available on this Id "+id);
//		}
//}
}
