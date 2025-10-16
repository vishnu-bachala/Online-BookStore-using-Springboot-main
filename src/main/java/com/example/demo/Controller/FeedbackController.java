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
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Feedback;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.CustomerNotFound;
import com.example.demo.Entity.Feedback;
import com.example.demo.Service.Impl.BookImpl;
import com.example.demo.Service.Impl.FeedbackImpl;

@RestController
public class FeedbackController {

	@Autowired
	FeedbackImpl feedbackImpl;
	
	
	
	@GetMapping("/feedback")
	public List<Feedback> getAllFeedbacks(){
		return feedbackImpl.getallFeedbacks();
	}
	
	@PostMapping("/feedback")
	public ResponseEntity<Object> addnewFeedback(@RequestBody @Valid Feedback f) throws BookNotFound{
		Feedback savedFeedback = feedbackImpl.addFeedback( f);
		
		return new ResponseEntity<Object>(savedFeedback, HttpStatus.CREATED);
	}
	
	@GetMapping("/feedback/{id}")
	public Feedback FindCustomerByCustomerid(@PathVariable int id) throws BookNotFound {
		return feedbackImpl.findFeedbackbyID(id);
	}
	
	@PutMapping("/feedback/{id}")
	public Feedback updateFeedbackbyId(@PathVariable int id,@RequestBody @Valid Feedback f) throws BookNotFound{
		return feedbackImpl.UpdateFeedbackbyID(id,f);
		}
	
	@DeleteMapping("/feedback/{id}")
	public String deleteFeedbackbyId(@PathVariable int id) throws BookNotFound {
		feedbackImpl.deleteFeedback(id);
		return "Feedback is deleted.";
	}
}
