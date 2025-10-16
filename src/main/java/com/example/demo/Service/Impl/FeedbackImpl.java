package com.example.demo.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Feedback;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.CustomerNotFound;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.FeedbackRepository;
import com.example.demo.Service.FeedbackService;

@Service
public class FeedbackImpl implements FeedbackService{

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Override
	public Feedback addFeedback(Feedback f) throws BookNotFound {

		Optional<Book> b1=bookRepository.findById(f.getBookid());
		if (b1.isPresent()){
		Book book1=bookRepository.findById(f.getBookid()).get();	
		List<Feedback> list1=book1.getReviews();
		
		f.setBook(book1);
		list1.add(f);
		book1.setReviews(list1);
		if(book1.getUserratings()==0) {
			book1.setUserratings((book1.getUserratings()+f.getRatings()));
		}	
		else {
			book1.setUserratings((book1.getUserratings()+f.getRatings())/2);
		}
		bookRepository.save(book1);
				return feedbackRepository.save(f);
		
		}else {
			throw new BookNotFound("No Book Found on this Id: "+f.getBookid());
		}
		
		
		
		
	}

	@Override
	public void deleteFeedback(int id) throws BookNotFound {
		Optional<Feedback> op=feedbackRepository.findById(id);
		if (op.isPresent()) {
			
		feedbackRepository.deleteById(id);
		}
		else {
			throw new BookNotFound("No Feedback Found on this Id.:"+id);
		}
		
	}

	@Override
	public List<Feedback> getallFeedbacks() {
		// TODO Auto-generated method stub
		
		return feedbackRepository.findAll();
	}

	@Override
	public Feedback findFeedbackbyID(int id) throws BookNotFound {
		Optional<Feedback> op=feedbackRepository.findById(id);
		if (op.isPresent()) {		
			 Feedback f1=feedbackRepository.findById(id).get();
			 return f1;
		}
		else {
			throw new BookNotFound("No Feedback Found wit the id: "+id);
		}
	}

	@Override
	public Feedback UpdateFeedbackbyID(int id, Feedback f) throws BookNotFound {

		Optional<Feedback> op = feedbackRepository.findById(id);
		if (op.isPresent()) {
			Feedback f1=feedbackRepository.findById(id).get();
			f1.setReview(f.getReview());
		return feedbackRepository.save(f1);
		}
		else{
			throw new BookNotFound("No Feedback found with this Id :"+id);
		}
		
	}

}
