package com.jayanth.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayanth.blog.entities.Category;
import com.jayanth.blog.entities.Post;
import com.jayanth.blog.entities.User;
import com.jayanth.blog.payload.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByCategory(Category category);
	List<Post> findByUser(User user);
}
