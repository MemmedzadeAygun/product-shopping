package az.developia.full_stack_ecommerce.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.full_stack_ecommerce.dto.OrderRequestDto;
import az.developia.full_stack_ecommerce.entity.Cart;
import az.developia.full_stack_ecommerce.entity.Order;
import az.developia.full_stack_ecommerce.exception.OurRuntimeException;
import az.developia.full_stack_ecommerce.repository.CartRepository;
import az.developia.full_stack_ecommerce.repository.OrderRepository;
import jakarta.validation.Valid;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ModelMapper mapper;

	public void addToOrder(@Valid OrderRequestDto dto) {
		Cart cart = cartRepository.findById(dto.getCartId())
	            .orElseThrow(() -> new OurRuntimeException(null, "Cart not found"));

	    Order order = new Order();
	    order.setFirstName(dto.getFirstName());
	    order.setLastName(dto.getLastName());
	    order.setState(dto.getState());
	    order.setCity(dto.getCity());
	    order.setAddress(dto.getAddress());
	    order.setZipCode(dto.getZipCode());
	    order.setPhone(dto.getPhone());
	    order.setEmail(dto.getEmail());
	    order.setCardNumber(dto.getCardNumber());
	    order.setExpiryMonth(dto.getExpiryMonth());
	    order.setExpiryYear(dto.getExpiryYear());
	    order.setSecurityCode(dto.getSecurityCode());

	    order.setCart(cart);

	    orderRepository.save(order);
	}

}
