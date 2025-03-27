package com.codewithwaruni.asset.services;

import java.util.List;

import com.codewithwaruni.asset.entities.Post;
import com.codewithwaruni.asset.payloads.PostDto;
import com.codewithwaruni.asset.payloads.PostResponse;

public interface PostService {
	//create 
	PostDto createPost(PostDto  postDto,Integer userId,Integer categoryId);
	
	//update 
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	PostResponse getAllPost(Integer pageNumber,Integer pageSize);
	
	
	//get single post
	
	PostDto getPostById(Integer postId);
	
	//get all posts by cayegory
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	
	//get all post posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<PostDto> searchPosts(String keyword);

}
