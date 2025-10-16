package com.example.demo.ExceptionHandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {



	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String msg = error.getDefaultMessage();
			map.put(fieldName, msg);
		});
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(UsernameNotFoundException.class)

	public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get User");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}

	@ExceptionHandler(CategoryNotFound.class)

	public ResponseEntity<Object> handleCategoryNotFound(CategoryNotFound ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get Category");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}
	@ExceptionHandler(CustomerNotFound.class)

	public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFound ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get Customer");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}

	@ExceptionHandler(BookNotFound.class)

	public ResponseEntity<Object> handleBookNotFound(BookNotFound ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get Book");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}
	
	@ExceptionHandler(OrderItemNotFound.class)

	public ResponseEntity<Object> handleOrderItemNotFound(OrderItemNotFound ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get OrderItem");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}
	@ExceptionHandler(PublisherNotFound.class)

	public ResponseEntity<Object> handlePublisherNotFound(PublisherNotFound ex) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Description", "Trying to get Publisher");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(ex.getMessage());

	}
	
}
