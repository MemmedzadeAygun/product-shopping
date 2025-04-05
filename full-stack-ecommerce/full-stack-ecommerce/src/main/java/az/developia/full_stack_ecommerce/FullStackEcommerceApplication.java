package az.developia.full_stack_ecommerce;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FullStackEcommerceApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper m = new ModelMapper();
		return m;
	}

	public static void main(String[] args) {
		SpringApplication.run(FullStackEcommerceApplication.class, args);
	}

}
