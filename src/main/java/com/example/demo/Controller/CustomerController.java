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
import com.example.demo.Entity.Customer;
import com.example.demo.ExceptionHandling.CustomerNotFound;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.Impl.CustomerImpl;
@RestController
public class CustomerController {
	@Autowired
	CustomerImpl customerImpl;
	
	@GetMapping("/customer")
	public List<Customer> findAllCustomers() {
		return customerImpl.findAllCustomers();
	}

	@PostMapping("/customer")
	public ResponseEntity<Object> AddNewCustomer(@RequestBody @Valid Customer d) {
		Customer savedCustomer = customerImpl.addCustomer(d);
		return new ResponseEntity<Object>(savedCustomer, HttpStatus.CREATED);
	}

	@GetMapping("/customer/{id}")
	public Customer FindCustomerByCustomerid(@PathVariable int id) throws CustomerNotFound {
		return customerImpl.findCustomerById(id);
	}
	
	@PutMapping("/customer/{id}")
	public Customer updateCustomerbyId(@PathVariable int id,@RequestBody @Valid Customer c) throws CustomerNotFound{
		return customerImpl.updateCustomer(c, id);
		}
	
	@DeleteMapping("/customer/{id}")
	public String deleteCustomer(@PathVariable int id) {
		customerImpl.deleteCustomer(id);
		return "Customer is deleted";
	}
	
}
