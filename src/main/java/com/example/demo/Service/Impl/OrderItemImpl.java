package com.example.demo.Service.Impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.OrderItem;
import com.example.demo.ExceptionHandling.BookNotFound;
import  com.example.demo.ExceptionHandling.CategoryNotFound;
import com.example.demo.ExceptionHandling.CustomerNotFound;
import com.example.demo.ExceptionHandling.OrderItemNotFound;
import com.example.demo.Repository.BookRepository;
import com.example.demo.Repository.CustomerRepository;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Service.OrderItemService;
@Service
public class OrderItemImpl implements OrderItemService{

	@Autowired 
	OrderItemRepository orderItemRepository;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	public OrderItem addOrderItem(OrderItem ot) throws BookNotFound {
		// TODO Auto-generated method stub
		Customer c1=customerRepository.findById(ot.getCust_id()).get();
		Book b1=bookRepository.findById(ot.getPurchasebook_id()).get();
		if (b1.getQuantityAvailable()>=ot.getQuantity())      //checks whether the quantity of books added by user is available in inventory.	
		{
		ot.setCustomer(c1);
		LocalDateTime myDateObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");  //generates Order  Date and Time
		String formattedDate = myDateObj.format(myFormatObj);
		ot.setOrderDate(formattedDate);
		
		ot.setBook(b1);
		ot.setTotalprice(b1.getPrice()*ot.getQuantity());
		ot.setDeliveryStatus("Delivery Pending.");
		b1.setQuantityAvailable(b1.getQuantityAvailable()-ot.getQuantity());
		if(b1.getQuantityAvailable()<=0) {   //if the book stock runs out book will be deleted
			bookRepository.delete(b1);
		}
		bookRepository.save(b1);
		
		
		return orderItemRepository.save(ot);
		}
		else {
			
			throw new BookNotFound("the "+b1.getTitle()+" Book Stock is limited so you can only add "+b1.getQuantityAvailable()
			+" books to your order");
		}
	}

	@Override
	public List<OrderItem> findAllOrderItems() {
		// TODO Auto-generated method stub
		return orderItemRepository.findAll();
	}

	@Override
	public OrderItem updateOrderItem(int otid,OrderItem ot) throws OrderItemNotFound, BookNotFound {

		Optional<OrderItem> op = orderItemRepository.findById(otid);
		if (op.isPresent()) {
			
			OrderItem ot1=orderItemRepository.findById(otid).get();
			if(ot1.getDeliveryStatus().equals("Delivery Pending.")) {
			Customer c1=customerRepository.findById(ot.getCust_id()).get();
			ot1.setCustomer(c1);
			Book b1=bookRepository.findById(ot.getPurchasebook_id()).get();
			if(b1.getQuantityAvailable()>=ot.getQuantity())  
			{
			ot1.setBook(b1);
			if(ot1.getQuantity()!=ot.getQuantity()) {
			if (ot1.getQuantity()>=ot.getQuantity()) {    //if the user decreases book quantity
			int diff=ot1.getQuantity()-ot.getQuantity();
			b1.setQuantityAvailable(b1.getQuantityAvailable()+diff);
			bookRepository.save(b1);
			}
			else {											//if the user decreases book quantity
				int diff=ot.getQuantity()-ot1.getQuantity();
				b1.setQuantityAvailable(b1.getQuantityAvailable()-diff);
				bookRepository.save(b1);	
			}
			}
				ot1.setQuantity(ot.getQuantity());
			
			ot1.setCust_id(ot.getCust_id());
			ot1.setPurchasebook_id(ot.getPurchasebook_id());
		
			
			ot1.setTotalprice(b1.getPrice()*ot1.getQuantity());  //sets total price of order
			
			if(b1.getQuantityAvailable()<=0) {		//if the book stock runs out book will be deleted
				bookRepository.delete(b1);
			}
			bookRepository.save(b1);
			
		
			return orderItemRepository.save(ot1);
			
			}
			if(b1.getQuantityAvailable()<ot.getQuantity()) {  //if user is adding more book than available in inventory
				throw new BookNotFound("the "+b1.getTitle()+" Book Stock is limited so you can only add "+b1.getQuantityAvailable()
				+" books to your order");			}
		}
		else {
			throw new OrderItemNotFound("Order cannot be updated as the order is already Confirmed and verified.");
		}
		}else {
				throw new OrderItemNotFound("Order Not found on this id "+otid);

			}
				
		return null;
		
		
		
	}

	@Override
	public void deleteOrderItem(int otid) {
		// TODO Auto-generated method stub
		Optional<OrderItem> op = orderItemRepository.findById(otid);
		if (op.isPresent()) {
			OrderItem ot=orderItemRepository.findById(otid).get();
			Book b1=bookRepository.findById(otid).get();
			b1.setQuantityAvailable(ot.getQuantity()+b1.getQuantityAvailable());		//if an order is deleted the ordered books are sent back to the inventory stock.
			bookRepository.save(b1);
		orderItemRepository.deleteById(otid);
		
		}
		else {
			System.out.println("OrderItem is not present in this id: "+otid);
		}
	}

	@Override
	public OrderItem findOrderItemById(int otid) throws OrderItemNotFound {
		// TODO Auto-generated method stub
		Optional<OrderItem> optional = orderItemRepository.findById(otid);
		if (optional.isPresent()) {
		return orderItemRepository.findById(otid).get();
		} else {
			throw new OrderItemNotFound("OrderItem is not available in database of this id: "+otid);
		}
	}

	@Override
	public OrderItem completeDelivery(int otid) throws OrderItemNotFound {
		// TODO Auto-generated method stub
		Optional<OrderItem> optional = orderItemRepository.findById(otid);
		if (optional.isPresent()) {
			OrderItem ot=orderItemRepository.findById(otid).get();
			if(ot.getDeliveryStatus().equals("Delivery Pending.")) {
			ot.setDeliveryStatus("Order Confirmed.");
			return orderItemRepository.save(ot);
			}
			else {
				throw new OrderItemNotFound("This Order is already Verified and Confirmed. ");
			}
		}
		else {
			throw new OrderItemNotFound("Order not found on this Id: "+otid);
		}
	}


}
