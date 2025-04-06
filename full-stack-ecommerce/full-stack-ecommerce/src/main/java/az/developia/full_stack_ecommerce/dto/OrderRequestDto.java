package az.developia.full_stack_ecommerce.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

	private Integer cartId;
	
	@NotBlank(message = "First Name cannot be empty.")
	@Size(min = 2, max = 50, message = "First Name should be between 2 and 50 characters.")
	private String firstName;
	
	@NotBlank(message = "Last Name cannot be empty.")
    @Size(min = 2, max = 50, message = "Last Name should be between 2 and 50 characters.")
	private String lastName;
	
	@NotBlank(message = "State cannot be empty.")
	private String state;
	
	@NotBlank(message = "City cannot be empty.")
	private String city;
	
	@NotBlank(message = "Address cannot be empty.")
	private String address;
	 
	@NotNull(message = "Zip code cannot be null.")
	private Integer zipCode;
	
	@NotBlank(message = "Phone number cannot be empty.")
	private String phone;
	
	@Pattern(regexp = "[a-z]+@[a-z]+\\.[a-z]{2,4}",message = "emaili duz yaz!!")
	private String email;
	
	@NotBlank(message = "Card number cannot be empty.")
	private String cardNumber;
	
	@Min(value = 1, message = "Expiry month must be between 1 and 12.")
    @Max(value = 12, message = "Expiry month must be between 1 and 12.")
	@NotNull(message = "Expirymonth cannot be null.")
	private Integer expiryMonth;
	
	@NotNull(message = "Expiryyear cannot be null.")
	@Min(value = 23, message = "Expiry year must be greater than or equal to the current year.")
	private Integer expiryYear;
	
	@NotNull(message = "Security code cannot be null.")
    @Min(value = 100, message = "Security code must be 3 digits.")
    @Max(value = 999, message = "Security code must be 3 digits.")
	private Integer securityCode;
}
