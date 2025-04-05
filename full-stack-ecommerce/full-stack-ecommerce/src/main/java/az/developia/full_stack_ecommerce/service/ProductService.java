package az.developia.full_stack_ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.full_stack_ecommerce.dto.ProductRequestDto;
import az.developia.full_stack_ecommerce.entity.Product;
import az.developia.full_stack_ecommerce.entity.User;
import az.developia.full_stack_ecommerce.exception.OurRuntimeException;
import az.developia.full_stack_ecommerce.repository.ProductRepository;
import az.developia.full_stack_ecommerce.repository.UserRepository;
import az.developia.full_stack_ecommerce.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public void addProduct(@Valid ProductRequestDto dto) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.getUserByUsername(username);
		Integer id = user.getId();

		Product product = new Product();
		modelMapper.map(dto, product);
		product.setId(null);
		product.setUserId(id);
		productRepository.save(product);
	}

	public List<ProductResponse> getAllProductsByUserId() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.getUserByUsername(username);
		Integer id = user.getId();

		List<Product> products = productRepository.findAllByUserId(id);
		return products.stream().map(product -> modelMapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
	}

	public List<ProductResponse> getAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(product -> modelMapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
	}

	public ProductResponse getById(Integer id) {
		if (id == null || id <= 0) {
			throw new OurRuntimeException(null, "id is absolute");
		}
		Optional<Product> byId = productRepository.findById(id);
		if (byId.isPresent()) {
			return modelMapper.map(byId.get(), ProductResponse.class);
		} else {
			throw new OurRuntimeException(null, "id not found");
		}
	}

	public void update(ProductRequestDto dto) {
		if (dto.getId() == null || dto.getId() <= 0) {
			throw new OurRuntimeException(null, "id is absolute");
		}

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.getUserByUsername(username);
		Integer id = user.getId();

		Optional<Product> existingProduct = productRepository.findById(dto.getId());
		if (existingProduct.isPresent()) {
			Product product = existingProduct.get();
			modelMapper.map(dto, product);
			product.setUserId(id);
			productRepository.save(product);
		} else {
			throw new OurRuntimeException(null, "id not found");
		}
	}

	public void delete(Integer id) {
		if (id == null || id <= 0) {
			throw new OurRuntimeException(null, "id is absolute");
		}
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
		} else {
			throw new OurRuntimeException(null, "id not found");
		}
	}

	public List<ProductResponse> searchProducts(String query) {
		List<Product> products = productRepository.findAll();

		return products.stream().filter(product -> product.getModel().toLowerCase().contains(query.toLowerCase()))
				.map(product -> modelMapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
	}

}
