package com.example.demo.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Entity.Publisher;
import com.example.demo.ExceptionHandling.OrderItemNotFound;
import com.example.demo.ExceptionHandling.PublisherNotFound;
import com.example.demo.Repository.PublisherRepository;
import com.example.demo.Service.PublisherService;
@Service
public class PublisherImpl implements PublisherService{
	
	@Autowired
	PublisherRepository publisherRepository;
	
	@Override
	public Publisher addPublisher(Publisher p) throws PublisherNotFound {
		// TODO Auto-generated method 
		Optional<Publisher> optional = publisherRepository.findBycontactNumber(p.getContactNumber());
		if (optional.isPresent()) {
			throw new PublisherNotFound("Publisher already Exists with the same Mobile Number "+p.getContactNumber()
			+" Kindly choose a Different Number.");
		}		else {
			return publisherRepository.save(p);

		}	
	}

	@Override
	public List<Publisher> findAllPublishers() {
		// TODO Auto-generated method stub
		return publisherRepository.findAll();
	}

	@Override
	public Publisher updatePublisher(Publisher p,int pid) throws PublisherNotFound {
		Optional<Publisher> op = publisherRepository.findById(pid);
		if (op.isPresent()) {
			Publisher p1=publisherRepository.findById(pid).get();
			p1.setName(p.getName());
			p1.setAddress(p.getAddress());
			p1.setContactNumber(p.getContactNumber());
			
			return publisherRepository.save(p1);
		}else{
			System.out.println("Publisher record is not present in this id :"+pid);
			throw new PublisherNotFound("Publisher record is not present in this id :"+pid);
		}
		
	}

	@Override
	public void deletePublisher(int pid) {
		Optional<Publisher> op = publisherRepository.findById(pid);
		if (op.isPresent())
		{
			publisherRepository.deleteById(pid);
		} else {
			System.out.println("Category record is not present in this id:"+pid);
		}
	}

	@Override
	public Publisher findPublisherById(int pid) throws PublisherNotFound {
		Optional<Publisher> optional = publisherRepository.findById(pid);
		if (optional.isPresent()) {
		return publisherRepository.findById(pid).get();
		} else {
			throw new PublisherNotFound("Publisher is not available in database");
		}
	}



}
