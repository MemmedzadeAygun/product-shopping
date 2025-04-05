package az.developia.full_stack_ecommerce.controller;

import java.util.List;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import az.developia.full_stack_ecommerce.dto.ProductRequestDto;
import az.developia.full_stack_ecommerce.exception.OurRuntimeException;
import az.developia.full_stack_ecommerce.response.ProductResponse;
import az.developia.full_stack_ecommerce.service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/products")
@CrossOrigin(origins = "*")
public class ProductController {
	
	@Autowired
	private ProductService service;

	
	@PostMapping(path = "/create")
	public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequestDto dto, BindingResult br){
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "melumatlarin tamliginda problem var");
		}
		
		service.addProduct(dto);
		return ResponseEntity.ok("Product create successfully");
	}
	
	@GetMapping(path = "/getAllByUserId")
	public List<ProductResponse> getProducts() {
		return service.getAllProductsByUserId();
	}
	
	@GetMapping(path = "/getAllProducts")
	public List<ProductResponse> getAllProduct(){
		return service.getAll();
	}
	
	@GetMapping(path = "/searchProduct")
	public List<ProductResponse> searchProducts(@RequestParam(name = "query") String query){
		return service.searchProducts(query);
	}
	
	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable Integer id) {
		return service.getById(id);
	}
	
	@PutMapping(path = "/update")
	public void updateProduct(@RequestBody ProductRequestDto dto, BindingResult br) {
		if (br.hasErrors()) {
			throw new OurRuntimeException(br, "melumatlarin tamliginda problem var");
		}
		service.update(dto);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.ok("Product delete successfully");
	}
}
