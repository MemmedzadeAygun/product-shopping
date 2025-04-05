package az.developia.full_stack_ecommerce.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	private Integer id;
	private String brand;
	private String model;
	private String category;
	private String description;
	private Double price;
	private Integer rating;
	private String imgUrl;
}
