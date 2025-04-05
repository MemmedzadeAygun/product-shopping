package az.developia.full_stack_ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.full_stack_ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	List<Product> findAllByUserId(Integer id);

}
