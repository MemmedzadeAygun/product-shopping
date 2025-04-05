package az.developia.full_stack_ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.full_stack_ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	User getUserByUsername(String username);
}
