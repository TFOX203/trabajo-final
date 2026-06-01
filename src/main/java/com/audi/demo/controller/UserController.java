package com.audi.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audi.demo.domain.User;

/**
 * @author sasha
 */
@RestController
@RequestMapping
@CrossOrigin("*")
public class UserController {
	
	@GetMapping
	public String test() {
		
		return "hello";
		
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable long id) {
		
		return new User(id);
		
	}
	
	@GetMapping("/all")
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1));
		users.add(new User(2));
		return users;
		
	}

}
