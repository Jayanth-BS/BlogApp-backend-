package com.jayanth.blog.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//reason for this class is that User may involve derived attributes from the  data inputted so to only allow attributtes entered to be visible

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min= 4,message ="Name should be minimum of 4 characters !!")
	private String name;
	@Email(message = "Email address is invalid !!")
	private String email;
	@NotEmpty
	@Size(min = 4,max = 10,message="password shd be min4 and max 10 chars!!")
	private String password;
	@NotEmpty
	private String about;
}
