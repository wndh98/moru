package com.mogumogu.moru;

import com.mogumogu.moru.test.User;
import com.mogumogu.moru.test.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MoruApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testSaveUser() {
		// Given
		User user = new User("John", "john@example.com");

		// When
		User savedUser = userRepository.save(user);

		// Then
		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isNotNull();
		assertThat(savedUser.getName()).isEqualTo("John");
		assertThat(savedUser.getEmail()).isEqualTo("john@example.com");
	}

	@Test
	public void testFindById() {
		// Given
		User user = new User("Jane", "jane@example.com");
		User savedUser = userRepository.save(user);

		// When
		Optional<User> foundUser = userRepository.findById(savedUser.getId());

		// Then
		assertThat(foundUser).isPresent();
		assertThat(foundUser.get().getName()).isEqualTo("Jane");
	}

	@Test
	public void testFindAll() {
		// Given
		userRepository.save(new User("Alice", "alice@example.com"));
		userRepository.save(new User("Bob", "bob@example.com"));

		// When
		List<User> users = userRepository.findAll();

		// Then
		assertThat(users).hasSize(2);
	}

	@Test
	public void testDeleteUser() {
		// Given
		User user = new User("Charlie", "charlie@example.com");
		User savedUser = userRepository.save(user);

		// When
		userRepository.delete(savedUser);
		Optional<User> deletedUser = userRepository.findById(savedUser.getId());

		// Then
		assertThat(deletedUser).isNotPresent();
	}

}
