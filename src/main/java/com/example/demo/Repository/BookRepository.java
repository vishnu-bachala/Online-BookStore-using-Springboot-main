package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Book;
import com.example.demo.ExceptionHandling.BookNotFound;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{
	
public List<Book> findBookByTitle(String title);

}
