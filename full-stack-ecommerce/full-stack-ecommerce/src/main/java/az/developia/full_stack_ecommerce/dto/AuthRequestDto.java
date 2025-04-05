package az.developia.full_stack_ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	@Size(min = 2, max = 30, message = "Name must be min 2, max 30 characters")
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 30, message = "Surname must be min 2, max 30 characters")
	private String lastName;
	
	@Pattern(regexp = "[a-zA-Z]+@[a-z]+\\.[a-z]{2,4}")
	@NotNull
	private String email;
}
