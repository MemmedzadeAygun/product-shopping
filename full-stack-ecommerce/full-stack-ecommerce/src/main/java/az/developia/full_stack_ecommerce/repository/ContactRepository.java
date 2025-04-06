package az.developia.full_stack_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import az.developia.full_stack_ecommerce.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
