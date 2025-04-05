package az.developia.full_stack_ecommerce.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import az.developia.full_stack_ecommerce.dto.AuthRequestDto;
import az.developia.full_stack_ecommerce.entity.User;
import az.developia.full_stack_ecommerce.exception.InvalidCredentialsException;
import az.developia.full_stack_ecommerce.exception.OurRuntimeException;
import az.developia.full_stack_ecommerce.repository.UserRepository;
import az.developia.full_stack_ecommerce.response.AuthResponseDto;
import az.developia.full_stack_ecommerce.util.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public String register(AuthRequestDto dto) {
		if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
			throw new OurRuntimeException(null,"User is exists");
		}
		
		String hashPassword = passwordEncoder.encode(dto.getPassword());
		
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setPassword(hashPassword);
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		userRepository.save(user);
		
		return "User registered successfully!";
	}
	
	public AuthResponseDto login(AuthRequestDto dto) {
	    Optional<User> byUsername = userRepository.findByUsername(dto.getUsername());

	    if (byUsername.isEmpty() || !passwordEncoder.matches(dto.getPassword(), byUsername.get().getPassword())) {
	    	throw new InvalidCredentialsException("Invalid username or password");
	    }
	    
	    String token = jwtUtil.generateToken(
	    		byUsername.get().getUsername(),
	    		byUsername.get().getFirstName(),
	    		byUsername.get().getLastName(),
	    		byUsername.get().getEmail()
	    );
	    
	    AuthResponseDto response = new AuthResponseDto();
	    response.setFirstName(byUsername.get().getFirstName());
	    response.setLastName(byUsername.get().getLastName());
	    response.setUsername(byUsername.get().getUsername());
	    response.setEmail(byUsername.get().getEmail());
	    response.setToken(token);

	    return response;
	}
}

