package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.OrderItem;
import com.example.demo.Entity.Publisher;
import com.example.demo.ExceptionHandling.BookNotFound;
import com.example.demo.ExceptionHandling.OrderItemNotFound;
import com.example.demo.Repository.OrderItemRepository;
import com.example.demo.Service.OrderItemService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderItemsTest {
	@Autowired
	OrderItemService orderItemService;
	
	@MockBean
	OrderItemRepository otao;
	private Book book;
	private Customer customer;
	private OrderItem ot1,ot2;
	private Category category;
	private Publisher publisher;
	
	@BeforeEach
	void init() {
		customer=customer.builder().customerId(1).firstName("Amit").lastName("Bind")
		.email("bind9530@gmail.com").address("Sanjay Gandhi Nagar").phoneNumber("9134546489").build();
		
		book = book.builder().bookId(1).title("Linux").author("Saurabh").bookcategoryid(1).publicationDate(LocalDate.now())
				.price(250).description("Programming with Linux").quantityAvailable(50).bookpublisherid(1).category(category)
				.publisher(publisher).build();
		
		ot1=ot1.builder().book(book).orderItemId(1).customer(customer).quantity(2).purchasebook_id(1).cust_id(1).build();
		ot2=ot2.builder().book(book).orderItemId(2).customer(customer).quantity(4).purchasebook_id(1).cust_id(1).build();

	}
	@Test
	void testAddOrderItem() throws BookNotFound {
		Mockito.when(otao.save(ot1)).thenReturn(ot1);
		assertEquals(ot1, orderItemService.addOrderItem(ot1));
}
	@Test
	void testGetBook()throws BookNotFound, OrderItemNotFound {
		Optional<OrderItem> ot = Optional.of(ot1);

		Mockito.when(otao.findById(1)).thenReturn(ot);

		assertEquals(500, orderItemService.findOrderItemById(1).getTotalprice());	
	}

	@Test
	void testGetAll() {
		List<OrderItem> ot = Stream.of(ot1, ot2).collect(Collectors.toList());
		Mockito.when(otao.findAll()).thenReturn(ot);

		assertEquals(2, orderItemService.findAllOrderItems().size());
	}

	@Test
	void testDeleteBook() throws OrderItemNotFound {

		orderItemService.deleteOrderItem(2);
		Mockito.verify(otao).deleteById(2);

	}
}
	

