package az.developia.full_stack_ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.full_stack_ecommerce.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findAllByUserId(Integer userId);

}
