package az.developia.full_stack_ecommerce.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.developia.full_stack_ecommerce.dto.ContactRequestDto;
import az.developia.full_stack_ecommerce.entity.Contact;
import az.developia.full_stack_ecommerce.repository.ContactRepository;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	public void add(ContactRequestDto dto) {
		Contact entity = new Contact();
		mapper.map(dto, entity);
		repository.save(entity);
	}

}
