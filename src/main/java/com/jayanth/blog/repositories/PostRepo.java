package com.jayanth.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jayanth.blog.entities.Category;
import com.jayanth.blog.entities.Post;
import com.jayanth.blog.entities.User;
import com.jayanth.blog.payload.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByCategory(Category category);
	List<Post> findByUser(User user);
	//List<Post> findByTitleContaining(String title);
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);
//	Page<Post> findAll(Pageable p);
}
