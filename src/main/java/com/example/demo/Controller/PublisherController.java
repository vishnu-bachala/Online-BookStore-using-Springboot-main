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

import com.example.demo.Entity.Category;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Entity.Publisher;
import com.example.demo.ExceptionHandling.PublisherNotFound;
import com.example.demo.Service.Impl.PublisherImpl;

@RestController
public class PublisherController {

	@Autowired
	PublisherImpl publisherImpl;
	
	@GetMapping("/publisher")
	public List<Publisher> findAllPublishers() {
		return publisherImpl.findAllPublishers();
	}

	@PostMapping("/publisher")
	public ResponseEntity<Object> addNewPublisher(@RequestBody @Valid Publisher p) throws PublisherNotFound {
		Publisher p1=publisherImpl.addPublisher(p);
		return new ResponseEntity<Object>(p1,HttpStatus.ACCEPTED);
	}

	@GetMapping("/publisher/{id}")
	public Publisher findPublisherByPublisherid(@PathVariable int id) throws PublisherNotFound {
		return publisherImpl.findPublisherById(id);
	}

	@PutMapping("/publisher/{pid}")
	public Publisher updatePublisher(@PathVariable int pid,@RequestBody @Valid Publisher p) throws PublisherNotFound{
	return publisherImpl.updatePublisher(p,pid);
	}
	
	@DeleteMapping("/publisher/{id}")
	public String deletePublisher(@PathVariable int id) {
		publisherImpl.deletePublisher(id);
		return "Publisher is delted";
	}
}
