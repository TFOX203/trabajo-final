package com.audi.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.audi.demo.domain.User;
import com.audi.demo.service.UserService;


/**
 * @author sasha
 */
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
	
	//dependency injection
	@Autowired
	private UserService userService;
	
	
	@GetMapping
	public String test() {
		
		return "hello";
		
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable long id) {
		
		return userService.getUser(id);
		
	}
	
	/**
	 * @PostMapping is used to map a handler to a unique post request
	 * @param user
	 * @return
	 */
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	@GetMapping("/all")
	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1));
		users.add(new User(2));
		return users;
		
	}

}
