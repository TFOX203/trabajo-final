package com.audi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.audi.demo.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

}
