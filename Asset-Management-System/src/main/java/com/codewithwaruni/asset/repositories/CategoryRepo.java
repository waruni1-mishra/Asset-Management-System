package com.codewithwaruni.asset.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithwaruni.asset.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{
	

}