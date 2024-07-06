package com.jayanth.blog.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jayanth.blog.entities.Post;
import com.jayanth.blog.payload.PostDto;
import com.jayanth.blog.payload.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> searchPosts(String keyword);
	 
}
