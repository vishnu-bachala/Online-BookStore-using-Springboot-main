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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Category;
import com.example.demo.ExceptionHandling.CategoryNotFound;
import com.example.demo.Service.CategoryService;
import com.example.demo.Service.Impl.CategoryImpl;

@RestController
public class CategoryController {

	@Autowired
	CategoryImpl categoryImpl;
	
	@GetMapping("/category")
	public List<Category> findAllCategorys() {
		return categoryImpl.findAllCategorys();
	}

	@PostMapping("/category")
	public ResponseEntity<Category> AddNewCategory(@RequestBody @Valid Category c) throws CategoryNotFound {
		
		Category c1=categoryImpl.addCategory(c);
		
		return new ResponseEntity<>(c1, HttpStatus.CREATED);
	}

	@GetMapping("/category/{id}")
	public Category findCategoryByCategoryid(@PathVariable int id) throws CategoryNotFound {
		return categoryImpl.findCategoryById(id);
	}

	@PutMapping("/category/{cid}")
	public Category updateCategory(@PathVariable int cid,@RequestBody @Valid Category c) throws CategoryNotFound{
	return categoryImpl.updateCategory(c, cid);
	}
	
	@DeleteMapping("/category/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryImpl.deleteCategory(id);
		return "Category is deleted";
	}
}
