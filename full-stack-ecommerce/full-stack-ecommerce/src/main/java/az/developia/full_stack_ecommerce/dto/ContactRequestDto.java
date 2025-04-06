package az.developia.full_stack_ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactRequestDto {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Name is required")
	@Pattern(regexp = "[a-z]+@[a-z]+\\.[a-z]{2,4}",message = "emaili duz yaz!!")
	private String email;
	
	@NotBlank(message = "Name is required")
	private String phone;
	
	private String message;
}
