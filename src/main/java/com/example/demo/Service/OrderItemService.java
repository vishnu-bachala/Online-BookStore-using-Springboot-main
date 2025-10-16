package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.OrderItem;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.OrderItemNotFound;

public interface OrderItemService {

	public OrderItem addOrderItem(OrderItem ot) throws BookNotFound;

	public List<OrderItem> findAllOrderItems();

	public OrderItem updateOrderItem(int otid,OrderItem ot)throws OrderItemNotFound,BookNotFound;

	public void deleteOrderItem(int otid);

	public OrderItem findOrderItemById(int otid) throws OrderItemNotFound;
	
	public OrderItem completeDelivery(int otid)throws OrderItemNotFound;


}
