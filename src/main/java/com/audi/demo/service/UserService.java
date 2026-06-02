package com.audi.demo.service;

import org.springframework.stereotype.Service;

import com.audi.demo.domain.User;


/**
 * Service layer : it is in charge of business logic
 * the controller layer users this to manage user data
 * 
 */
@Service
public interface UserService {

	 User getUser(long id);
		
	

}
