package com.audi.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.audi.demo.domain.User;
import com.audi.demo.repository.UserRepository;
import com.audi.demo.service.UserServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

@Mock
private UserRepository userRepository;

@InjectMocks
private UserServiceImpl userService;

@Test
void getUser_deberiaRetornarUsuarioCuandoExiste() {
User user = new User();
user.setId(1L);
// si tu clase User tiene más campos, puedes setearlos aquí


when(userRepository.findById(1L)).thenReturn(Optional.of(user));

User result = userService.getUser(1L);

assertEquals(1L, result.getId());
verify(userRepository).findById(1L);
}
}