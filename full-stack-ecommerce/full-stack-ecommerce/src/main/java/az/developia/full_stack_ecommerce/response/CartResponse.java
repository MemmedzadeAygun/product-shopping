package az.developia.full_stack_ecommerce.response;

import az.developia.full_stack_ecommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {

	private Integer id;
	
	private Product product;
	
	private Integer quantity;
	
	private double subTotal;
}
