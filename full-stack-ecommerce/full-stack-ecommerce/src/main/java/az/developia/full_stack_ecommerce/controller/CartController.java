package az.developia.full_stack_ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.full_stack_ecommerce.dto.CartRequestDto;
import az.developia.full_stack_ecommerce.response.CartResponse;
import az.developia.full_stack_ecommerce.service.CartService;

@RestController
@RequestMapping(path = "/cart")
@CrossOrigin(origins = "*")
public class CartController {
	
	@Autowired
	private CartService cartService; 

	@PostMapping(path = "/add")
	public ResponseEntity<String> addToCart(@RequestBody CartRequestDto dto){
		cartService.addToCart(dto);
		return ResponseEntity.ok("Product add to cart successfully");
	}
	
	@GetMapping(path = "/getCards")
	public List<CartResponse> getAllCards(){
		return cartService.getCards();
	}
	
	@PutMapping(path = "/update")
	public ResponseEntity<String> updateCart(@RequestBody CartRequestDto dto){
		cartService.update(dto);
		return ResponseEntity.ok("Cart update successfully");
	}
	
	@DeleteMapping(path = "/{id}")
	public void deleteFromCart(@PathVariable Integer id) {
		cartService.delete(id);
	}
}
