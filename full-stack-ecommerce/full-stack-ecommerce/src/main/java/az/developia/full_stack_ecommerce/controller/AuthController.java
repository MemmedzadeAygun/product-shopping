package az.developia.full_stack_ecommerce.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.full_stack_ecommerce.dto.AuthRequestDto;
import az.developia.full_stack_ecommerce.exception.OurRuntimeException;
import az.developia.full_stack_ecommerce.response.AuthResponseDto;
import az.developia.full_stack_ecommerce.service.AuthService;
import az.developia.full_stack_ecommerce.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

	private final AuthService authService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping(path = "/register")
	public String register(@Valid @RequestBody AuthRequestDto request,BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br,"melumatlarin tamliginda problem var");
		}
		return authService.register(request);
	}

	@PostMapping(path = "/login")
	public AuthResponseDto login(@RequestBody AuthRequestDto request) {
		return authService.login(request);
	}
	
	@GetMapping(path = "/profile")
	public ResponseEntity<Map<String, String>> getUserProfile(@RequestHeader("Authorization") String token){
		if (token.startsWith("Bearer")) {
			token = token.substring(7);
		}
		
		Map<String, String> claims = jwtUtil.extractClaims(token);
		
		return ResponseEntity.ok(claims);
	}
}

