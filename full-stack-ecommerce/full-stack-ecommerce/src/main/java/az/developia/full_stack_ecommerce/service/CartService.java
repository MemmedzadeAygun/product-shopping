package az.developia.full_stack_ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.full_stack_ecommerce.dto.CartRequestDto;
import az.developia.full_stack_ecommerce.entity.Cart;
import az.developia.full_stack_ecommerce.entity.Product;
import az.developia.full_stack_ecommerce.entity.User;
import az.developia.full_stack_ecommerce.exception.OurRuntimeException;
import az.developia.full_stack_ecommerce.repository.CartRepository;
import az.developia.full_stack_ecommerce.repository.ProductRepository;
import az.developia.full_stack_ecommerce.repository.UserRepository;
import az.developia.full_stack_ecommerce.response.CartResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService {
	
	private final CartRepository repository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	

	public void addToCart(CartRequestDto dto) {
		Product product = productRepository.findById(dto.getProductId())
				.orElseThrow(() -> new OurRuntimeException(null, "product not found"));
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User userByUsername = userRepository.getUserByUsername(username);
		Integer id = userByUsername.getId();
		
		Integer quantity = (dto.getQuantity() == null || dto.getQuantity() <= 0) ? 1 : dto.getQuantity();
		
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setQuantity(quantity);
		Double subTotal = product.getPrice() * quantity;

		cart.setSubTotal(subTotal);
		cart.setUserId(id);
		repository.save(cart);
		
	}


	public List<CartResponse> getCards() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User userByUsername = userRepository.getUserByUsername(username);
		Integer userId=userByUsername.getId();
		
		List<Cart> cards = repository.findAllByUserId(userId);

		return cards.stream().map(card -> modelMapper.map(card, CartResponse.class)).collect(Collectors.toList());
	}


	public void update(CartRequestDto dto) {
		Cart cart = repository.findById(dto.getId())
				.orElseThrow(() -> new OurRuntimeException(null, "Cart not found"));

		Integer quantity = (dto.getQuantity() == null || dto.getQuantity() <= 0) ? 1 : dto.getQuantity();
		
		cart.setQuantity(quantity);

		Double subTotal = cart.getProduct().getPrice() * quantity;

		cart.setSubTotal(subTotal);
		repository.save(cart);
	}


	public void delete(Integer id) {
		if (id==null || id<=0) {
			throw new OurRuntimeException(null, "id absolute");
		}
		if (repository.findById(id).isPresent()) {
			repository.deleteById(id);
		}else {
			throw new OurRuntimeException(null, "id not found");
		}
	}

}
