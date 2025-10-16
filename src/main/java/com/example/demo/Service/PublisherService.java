package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Publisher;
import com.example.demo.ExceptionHandling.PublisherNotFound;

public interface PublisherService {

	public Publisher addPublisher(Publisher p) throws PublisherNotFound;

	public List<Publisher> findAllPublishers();

	public Publisher updatePublisher(Publisher p,int pid)throws PublisherNotFound;

	public void deletePublisher(int pid);

	public Publisher findPublisherById(int pid) throws PublisherNotFound;
	
}
