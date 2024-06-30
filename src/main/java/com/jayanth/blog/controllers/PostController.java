package com.jayanth.blog.controllers;

import java.util.List;

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

import com.jayanth.blog.payload.ApiResponse;
import com.jayanth.blog.payload.PostDto;
import com.jayanth.blog.services.PostService;

@RestController
@RequestMapping("api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/User/{userId}/Category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto newpostDto = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(newpostDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/User/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> posts = this.postService.getPostByUser(userId); 
		return new ResponseEntity<>(posts,HttpStatus.OK);
	}
	
	@GetMapping("/Category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> newPosts = this.postService.getPostByCategory(categoryId); 
		return new ResponseEntity<>(newPosts,HttpStatus.OK);
	}	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> newPosts = this.postService.getAllPosts();
		return new ResponseEntity<>(newPosts,HttpStatus.OK);
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto newPost = this.postService.getPostById(postId); 
		return new ResponseEntity<>(newPost,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId); 
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Successfully Deleted",true),HttpStatus.OK);
	}
	@PutMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		this.postService.updatePost(postDto, postId); 
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Successfully Updated",true),HttpStatus.OK);
	}
}
