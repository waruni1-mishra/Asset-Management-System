package com.codewithwaruni.asset.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithwaruni.asset.entities.Category;
import com.codewithwaruni.asset.entities.Post;
import com.codewithwaruni.asset.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleContaining(String title);

}
