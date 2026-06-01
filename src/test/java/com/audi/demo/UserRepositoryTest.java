package com.audi.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.audi.demo.domain.User;
import com.audi.demo.repository.UserRepository;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // ----------------------------------------------------------------
    //  Setup: limpiar la BD antes de cada test para evitar interferencias
    // ----------------------------------------------------------------
    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    // ================================================================
    //  SAVE  –  Guardar
    // ================================================================

    @Test
    @DisplayName("Guardar un usuario y recuperarlo por ID")
    void testSaveUser() {
        // Arrange
        User user = new User(1);
        user.setName("John Doe");
        user.setEmail("john@example.com");

        // Act
        User saved = userRepository.save(user);

        // Assert
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isPositive();
        assertThat(saved.getName()).isEqualTo("John Doe");
        assertThat(saved.getEmail()).isEqualTo("john@example.com");
    }

    @Test
    @DisplayName("Guardar varios usuarios y verificar que todos persisten")
    void testSaveMultipleUsers() {
        // Arrange
        User u1 = new User(1); u1.setName("Alice"); u1.setEmail("alice@example.com");
        User u2 = new User(2); u2.setName("Bob");   u2.setEmail("bob@example.com");
        User u3 = new User(3); u3.setName("Carol");  u3.setEmail("carol@example.com");

        // Act
        userRepository.saveAll(List.of(u1, u2, u3));

        // Assert
        assertThat(userRepository.count()).isEqualTo(3);
    }

    // ================================================================
    //  FIND  –  Buscar
    // ================================================================

    @Test
    @DisplayName("Buscar usuario por ID existente")
    void testFindById_exists() {
        // Arrange
        User user = new User(1);
        user.setName("Jane Doe");
        user.setEmail("jane@example.com");
        User saved = userRepository.save(user);

        // Act
        Optional<User> found = userRepository.findById(saved.getId());

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Jane Doe");
    }

    @Test
    @DisplayName("Buscar usuario por ID inexistente devuelve empty")
    void testFindById_notFound() {
        // Act
        Optional<User> found = userRepository.findById(999L);

        // Assert
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Obtener todos los usuarios")
    void testFindAll() {
        // Arrange
        User u1 = new User(1); u1.setName("Alice"); u1.setEmail("alice@example.com");
        User u2 = new User(2); u2.setName("Bob");   u2.setEmail("bob@example.com");
        userRepository.saveAll(List.of(u1, u2));

        // Act
        List<User> users = userRepository.findAll();

        // Assert
        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getName)
                         .containsExactlyInAnyOrder("Alice", "Bob");
    }

    // ================================================================
    //  UPDATE  –  Actualizar
    // ================================================================

    @Test
    @DisplayName("Actualizar el nombre de un usuario existente")
    void testUpdateUserName() {
        // Arrange
        User user = new User(1);
        user.setName("Old Name");
        user.setEmail("old@example.com");
        User saved = userRepository.save(user);

        // Act
        saved.setName("New Name");
        User updated = userRepository.save(saved);

        // Assert
        assertThat(updated.getName()).isEqualTo("New Name");
        assertThat(userRepository.count()).isEqualTo(1); // no se creó un registro nuevo
    }

    @Test
    @DisplayName("Actualizar el email de un usuario existente")
    void testUpdateUserEmail() {
        // Arrange
        User user = new User(1);
        user.setName("John");
        user.setEmail("old@example.com");
        User saved = userRepository.save(user);

        // Act
        saved.setEmail("new@example.com");
        userRepository.save(saved);

        // Assert
        Optional<User> found = userRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("new@example.com");
    }

    // ================================================================
    //  DELETE  –  Eliminar
    // ================================================================

    @Test
    @DisplayName("Eliminar un usuario por ID")
    void testDeleteById() {
        // Arrange
        User user = new User(1);
        user.setName("To Delete");
        user.setEmail("delete@example.com");
        User saved = userRepository.save(user);

        // Act
        userRepository.deleteById(saved.getId());

        // Assert
        assertThat(userRepository.findById(saved.getId())).isEmpty();
        assertThat(userRepository.count()).isZero();
    }

    @Test
    @DisplayName("Eliminar un usuario por entidad")
    void testDeleteByEntity() {
        // Arrange
        User user = new User(1);
        user.setName("Entity Delete");
        user.setEmail("entity@example.com");
        User saved = userRepository.save(user);

        // Act
        userRepository.delete(saved);

        // Assert
        assertThat(userRepository.existsById(saved.getId())).isFalse();
    }

    @Test
    @DisplayName("Eliminar todos los usuarios")
    void testDeleteAll() {
        // Arrange
        User u1 = new User(1); u1.setName("A"); u1.setEmail("a@example.com");
        User u2 = new User(2); u2.setName("B"); u2.setEmail("b@example.com");
        userRepository.saveAll(List.of(u1, u2));

        // Act
        userRepository.deleteAll();

        // Assert
        assertThat(userRepository.findAll()).isEmpty();
        assertThat(userRepository.count()).isZero();
    }

    // ================================================================
    //  EXIST / COUNT  –  Extras útiles
    // ================================================================

    @Test
    @DisplayName("Verificar existencia de usuario por ID")
    void testExistsById() {
        // Arrange
        User user = new User(1);
        user.setName("Exists");
        user.setEmail("exists@example.com");
        User saved = userRepository.save(user);

        // Act & Assert
        assertThat(userRepository.existsById(saved.getId())).isTrue();
        assertThat(userRepository.existsById(999L)).isFalse();
    }

    @Test
    @DisplayName("Contar usuarios en la base de datos")
    void testCount() {
        // Arrange
        assertThat(userRepository.count()).isZero();

        User u1 = new User(1); u1.setName("A"); u1.setEmail("a@example.com");
        User u2 = new User(2); u2.setName("B"); u2.setEmail("b@example.com");
        userRepository.saveAll(List.of(u1, u2));

        // Act & Assert
        assertThat(userRepository.count()).isEqualTo(2);
    }
}