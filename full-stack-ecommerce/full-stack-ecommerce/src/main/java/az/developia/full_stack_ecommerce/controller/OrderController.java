package az.developia.full_stack_ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.full_stack_ecommerce.dto.OrderRequestDto;
import az.developia.full_stack_ecommerce.exception.OurRuntimeException;
import az.developia.full_stack_ecommerce.service.OrderService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/orders")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping(path = "/add")
	public ResponseEntity<String> order(@Valid @RequestBody OrderRequestDto dto, BindingResult br){
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "Melumatlarin tamliginda problem var");
		}
		service.addToOrder(dto);
		return ResponseEntity.ok("Order was complated successfully");
	}
}
