package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.Category;
import com.example.demo.ExceptionHandling.CategoryNotFound;

public interface CategoryService {

	public Category addCategory(Category c) throws CategoryNotFound;

	public List<Category> findAllCategorys();

	public Category updateCategory(Category c,int cid)throws CategoryNotFound;

	public void deleteCategory(int cid);

	public Category findCategoryById(int cid) throws CategoryNotFound;


}
