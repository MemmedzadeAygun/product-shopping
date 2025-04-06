package az.developia.full_stack_ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Cart cart;
	private String firstName;
	private String lastName;
	private String state;
	private String city;
	private String address;
	private Integer zipCode;
	private String phone;
	private String email; 
	private String cardNumber;
	private Integer expiryMonth;
	private Integer expiryYear;
	private Integer securityCode;
	
}
