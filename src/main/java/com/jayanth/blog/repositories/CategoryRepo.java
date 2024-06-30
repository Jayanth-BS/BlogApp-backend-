package com.jayanth.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayanth.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
