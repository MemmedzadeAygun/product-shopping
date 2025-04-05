package az.developia.full_stack_ecommerce.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDto {
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String token;
}
