package com.audi.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.audi.demo.domain.User;
import com.audi.demo.repository.UserRepository;



@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUser(long id) {
		
		return userRepository.findById(id).get();
	}

	@Override
	public User saveUser(User user) {
		
		return userRepository.save(user);
	}

}
