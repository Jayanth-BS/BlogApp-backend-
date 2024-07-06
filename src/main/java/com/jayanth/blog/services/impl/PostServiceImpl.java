package com.jayanth.blog.services.impl;


import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jayanth.blog.entities.Category;
import com.jayanth.blog.entities.Post;
import com.jayanth.blog.entities.User;
import com.jayanth.blog.exceptions.ResourceNotFoundException;
import com.jayanth.blog.payload.PostDto;
import com.jayanth.blog.payload.PostResponse;
import com.jayanth.blog.payload.UserDto;
import com.jayanth.blog.repositories.CategoryRepo;
import com.jayanth.blog.repositories.PostRepo;
import com.jayanth.blog.repositories.UserRepo;
import com.jayanth.blog.services.PostService;

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
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId){
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

		Post post = this.modelMapper.map(postDto,Post.class);
		post.setImgName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost  = this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post"," id ",postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImgName(postDto.getImageName());
		Post updatedpost = this.postRepo.save(post);
		return this.modelMapper.map(updatedpost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," postId ",postId));
		this.postRepo.delete(post);
	}
	

	@Override
	public PostResponse getAllPosts(Integer pageNo, Integer pageSize,String sortBy,String sortDir) {
	    System.out.println(pageNo+" "+pageSize);
	    Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
	    PageRequest pageRequest = PageRequest.of(pageNo,pageSize,sort);
	    Page<Post> pageposts = this.postRepo.findAll(pageRequest);
	    List<PostDto> postDtos =  pageposts.getContent()
	    		.stream()
	    		.map((post) -> modelMapper.map(post, PostDto.class))
	    		.collect(Collectors.toList());
	    PostResponse  postResponse = new PostResponse();
	    postResponse.setContent(postDtos);
	    postResponse.setPageNumber(pageposts.getNumber());
	    postResponse.setPageSize(pageposts.getSize());
	    postResponse.setTotalElements(pageposts.getTotalElements());
	    postResponse.setTotalPages(pageposts.getTotalPages());
	    postResponse.setLastPage(pageposts.isLast());
	    return postResponse;
	}
	
	

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","PostId",postId));
		return this.modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
 		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User user =  this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserId",userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	
	@Override
	public List<PostDto> searchPosts(String keyword){
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		return posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	}

}
