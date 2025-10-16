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
import com.example.demo.Entity.OrderItem;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.CustomerNotFound;
import com.example.demo.ExceptionHandling.OrderItemNotFound;
import com.example.demo.Service.Impl.BookImpl;
import com.example.demo.Service.Impl.CustomerImpl;
import com.example.demo.Service.Impl.OrderItemImpl;

@RestController
public class OrderItemController {

	@Autowired
	OrderItemImpl orderItemImpl;
	
	@Autowired
	BookImpl bookImpl;
	
	@Autowired
	CustomerImpl customerImpl;
	
	
	
	@GetMapping("/orderItems")
	public List<OrderItem> findAllOrderItems() {
		return orderItemImpl.findAllOrderItems();
	}

	@PostMapping("/orderItems")
	public ResponseEntity<Object> AddNewOrderItem(@RequestBody @Valid OrderItem d) throws BookNotFound, CustomerNotFound {
		Book b=bookImpl.findBookById(d.getPurchasebook_id());
		
		Customer c=customerImpl.findCustomerById(d.getCust_id());
		
		OrderItem ot=orderItemImpl.addOrderItem(d);
		return new ResponseEntity<Object>(ot,HttpStatus.ACCEPTED);
	}

	@GetMapping("/orderItems/{id}")
	public OrderItem FindOrderItemByOrderItemid(@PathVariable int id) throws OrderItemNotFound {
		return orderItemImpl.findOrderItemById(id);
	}

	@PutMapping("/orderItems/{id}")
	public OrderItem UpdateOrderItem(@RequestBody @Valid OrderItem ot,@PathVariable int id) throws BookNotFound, CustomerNotFound, OrderItemNotFound {
		Book b=bookImpl.findBookById(ot.getPurchasebook_id());
		
		Customer c=customerImpl.findCustomerById(ot.getCust_id());
		
		return orderItemImpl.updateOrderItem(id, ot);
		
	}
	

	@DeleteMapping("/orderItems/{id}")
	public String deleteOrderItem(@PathVariable int id) throws OrderItemNotFound {
		if (orderItemImpl.findOrderItemById(id)!=null){
			orderItemImpl.deleteOrderItem(id);
			return "Order deleted Successfully.";
		}
		else {
		return "Order is not present on this id.";
		}
	}
	
	@PutMapping("/confirmorder/{otid}")
	public OrderItem completedeliverysatus(@PathVariable int otid) throws OrderItemNotFound {
		return orderItemImpl.completeDelivery(otid);
	}
	
	
	

}
