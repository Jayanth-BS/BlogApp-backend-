package com.jayanth.blog.payload;

import com.jayanth.blog.entities.Category;
import com.jayanth.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private String title;
	private String content;
	private String imageName;
	private CategoryDto category;
	private UserDto user;
	
}
