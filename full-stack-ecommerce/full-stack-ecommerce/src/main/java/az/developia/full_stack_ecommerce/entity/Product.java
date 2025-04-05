package az.developia.full_stack_ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String brand;

	private String model;

	private String category;

	@Column(columnDefinition = "TEXT")
	private String description;

	private Double price;

	private Integer rating;

	@Column(columnDefinition = "TEXT")
	private String imgUrl;
	private Integer userId;

}

