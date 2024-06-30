package com.jayanth.blog.services;

import java.util.List;

import com.jayanth.blog.entities.Post;
import com.jayanth.blog.payload.PostDto;

public interface PostService {
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto, Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getAllPosts();
	PostDto getPostById(Integer postId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
}
