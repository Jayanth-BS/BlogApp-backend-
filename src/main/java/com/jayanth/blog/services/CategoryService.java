package com.jayanth.blog.services;

import java.util.List;

import com.jayanth.blog.payload.CategoryDto;
import com.jayanth.blog.payload.UserDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategories();
	void deleteCategory(Integer categoryId);
}
