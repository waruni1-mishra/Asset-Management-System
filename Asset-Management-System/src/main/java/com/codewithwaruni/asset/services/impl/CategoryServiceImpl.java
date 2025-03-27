package com.codewithwaruni.asset.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithwaruni.asset.entities.Category;
import com.codewithwaruni.asset.exception.ResourceNotFoundException;
import com.codewithwaruni.asset.payloads.CategoryDto;
import com.codewithwaruni.asset.repositories.CategoryRepo;
import com.codewithwaruni.asset.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryRepo.save(cat);
		
		// TODO Auto-generated method stub
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id ",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatecat =this.categoryRepo.save(cat);
		
		// TODO Auto-generated method stub
		return this.modelMapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
		this.categoryRepo.delete(cat);
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> catDto = categories.stream().map((cat)-> this.modelMapper.map(cat ,CategoryDto.class)).collect(Collectors.toList());		// TODO Auto-generated method stub
		return catDto;
	}
	

}
