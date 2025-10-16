package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Customer;
import com.example.demo.ExceptionHandling.CustomerNotFound;
import com.example.demo.ExceptionHandling.OrderItemNotFound;

public interface CustomerService {

	public Customer addCustomer(Customer c);

	public List<Customer> findAllCustomers();

	public Customer updateCustomer(Customer c,int cid) throws CustomerNotFound;;

	public void deleteCustomer(int cid);

	public Customer findCustomerById(int cid) throws CustomerNotFound;

}
