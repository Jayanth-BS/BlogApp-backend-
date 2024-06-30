package com.jayanth.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayanth.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {

	private UserRepo userRepo;
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest() {
		String className = this.userRepo.getClass().getName();
		String packName = this.userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packName);
	}

}
