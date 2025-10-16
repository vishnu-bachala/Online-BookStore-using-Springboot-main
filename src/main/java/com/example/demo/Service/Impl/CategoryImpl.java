package com.example.demo.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Category;
import com.example.demo.Entity.Customer;
import com.example.demo.Entity.Publisher;
import com.example.demo.ExceptionHandling.CategoryNotFound;
import com.example.demo.ExceptionHandling.PublisherNotFound;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Service.CategoryService;
@Service
public class CategoryImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category c) throws CategoryNotFound {
Optional<Category> op=categoryRepository.findByName(c.getName());
		if (op.isPresent()) {
			throw new CategoryNotFound("Category already Exists with the same name "+c.getName());
		}		else {
			return categoryRepository.save(c);
			}
	}

	@Override
	public List<Category> findAllCategorys() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Category c,int cid) throws CategoryNotFound {
		// TODO Auto-generated method stub

		Optional<Category> op = categoryRepository.findById(cid);
		if (op.isPresent()) {
			Category c1=categoryRepository.findById(cid).get();
			c1.setName(c.getName());
			c1.setAgegroup(c.getAgegroup());
			c1.setLanguage(c.getLanguage());
			return categoryRepository.save(c1);
		}else{
			System.out.println("Category record is not present in this id :"+cid);
		throw new CategoryNotFound("Category record is not present in this id :"+cid);
		}
		
		
	}

	@Override
	public void deleteCategory(int cid) {
		Optional<Category> op = categoryRepository.findById(cid);
		if (op.isPresent())

		{
			categoryRepository.deleteById(cid);

		} else {
			System.out.println("Category record is not present in this id:"+cid);
		}

	}

	@Override
	public Category findCategoryById(int cid) throws CategoryNotFound{
		Optional<Category> optional = categoryRepository.findById(cid);
		if (optional.isPresent()) {
			return categoryRepository.findById(cid).get();
		} else {
			throw new CategoryNotFound("Category is not available in database of this id:"+cid);
		}
	}


}
