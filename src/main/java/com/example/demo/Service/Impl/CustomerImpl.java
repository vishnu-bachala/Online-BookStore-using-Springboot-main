package com.example.demo.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.OrderItem;
import com.example.demo.ExceptionHandling.CategoryNotFound;
import com.example.demo.ExceptionHandling.CustomerNotFound;
import com.example.demo.ExceptionHandling.OrderItemNotFound;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Service.CustomerService;
@Service
public class CustomerImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer addCustomer(Customer c) {
		// TODO Auto-generated method stub
		return customerRepository.save(c);
	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public Customer updateCustomer(Customer c,int cid) throws CustomerNotFound {
		// TODO Auto-generated method stub

		Optional<Customer> op = customerRepository.findById(cid);
		if (op.isPresent()) {
			Customer c1=customerRepository.findById(cid).get();
			c1.setFirstName(c.getFirstName());
			c1.setLastName(c.getLastName());
			c1.setEmail(c.getEmail());
			c1.setPhoneNumber(c.getPhoneNumber());
			c1.setAddress(c.getAddress());
		return customerRepository.save(c1);
		}
		else{
			throw new CustomerNotFound("No Customer found with this Id :"+cid);
		}
		
	}

	@Override
	public void deleteCustomer(int cid) {
		Optional<Customer> op = customerRepository.findById(cid);
		if (op.isPresent())

		{
			customerRepository.deleteById(cid);

		} else {
			System.out.println("Customer record is not present in this id:"+cid);
		}
	}

	@Override
	public Customer findCustomerById(int cid) throws CustomerNotFound {
		Optional<Customer> optional = customerRepository.findById(cid);
		if (optional.isPresent()) {
			return customerRepository.findById(cid).get();
		} else {
			throw new CustomerNotFound("Customer is not available in database of this Id:"+cid);
		}
	}

}
