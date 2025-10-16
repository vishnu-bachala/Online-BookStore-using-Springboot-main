package com.example.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Feedback;
import com.example.demo.ExceptionHandling.BookNotFound;


public interface FeedbackService {
	
	public Feedback addFeedback(Feedback f) throws BookNotFound;
	
	public List<Feedback> getallFeedbacks();

	public void deleteFeedback(int id) throws BookNotFound;
	
	public Feedback findFeedbackbyID(int id) throws BookNotFound;
	
	public Feedback UpdateFeedbackbyID(int id,Feedback f) throws BookNotFound;

	


}
