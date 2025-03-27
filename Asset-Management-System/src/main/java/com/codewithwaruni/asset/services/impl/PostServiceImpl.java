package com.codewithwaruni.asset.services.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.codewithwaruni.asset.entities.Category;
import com.codewithwaruni.asset.entities.Post;
import com.codewithwaruni.asset.entities.User;
import com.codewithwaruni.asset.exception.ResourceNotFoundException;
import com.codewithwaruni.asset.payloads.PostDto;
import com.codewithwaruni.asset.payloads.PostResponse;
import com.codewithwaruni.asset.repositories.CategoryRepo;
import com.codewithwaruni.asset.repositories.PostRepo;
import com.codewithwaruni.asset.repositories.UserRepo;
import com.codewithwaruni.asset.services.PostService;
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id", categoryId));
		
		
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());;
		post.setUser(user);;
		post.setCategory(category);
		
		
		Post newPost = this.postRepo.save(post);
		// TODO Auto-generated method stub
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		 Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		// TODO Auto-generated method stub
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
	 Post post= this.postRepo.findById(postId)
			 .orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
		// TODO Auto-generated method stub
        this.postRepo.delete(post);
	}

	@Override
	
		public PostResponse getAllPost(Integer pageNumber,Integer pageSize) {
		
		
		Pageable p= PageRequest.of(pageNumber, pageSize);
		
			Page<Post> pagePost = this.postRepo.findAll(p);
			//List<Post> pagePost = pagePost.getContent();
			
			List<Post> allPosts = pagePost.getContent();
			List<PostDto> postDtos = allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
			
			PostResponse postResponse=new PostResponse();
			postResponse.setContent(postDtos);
			postResponse.setPageNumber(pagePost.getNumber());
			postResponse.setPageSize(pagePost.getSize());
			postResponse.setTotalElements(pagePost.getNumberOfElements());
			postResponse.setTotalPage(pagePost.getTotalPages());
			postResponse.setLastPage(pagePost.isLast());
			
			
			// TODO Auto-generated method stub
			return postResponse;
		

		// TODO Auto-generated method stub
		
		}
	

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		return this.modelMapper.map(post, PostDto.class);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id",categoryId));
		  List<Post> posts = this.postRepo.findByCategory(cat);
		
		   List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return postDtos;
	}

	//@Override
	//public List<Post> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		//return null;
	//}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		// TODO Auto-generated method stub
		List<Post> posts =this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id",userId));
		// TODO Auto-generated method stub
		 List<Post> posts = this.postRepo.findByUser(user);
		  List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		 
		return postDtos;
	}
	

}
