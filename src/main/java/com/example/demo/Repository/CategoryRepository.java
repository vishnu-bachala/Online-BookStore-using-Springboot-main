package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Publisher;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	public Optional<Category> findByName(String name);

	
}
