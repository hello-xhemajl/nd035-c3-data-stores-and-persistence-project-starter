package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.user.customer.model.Customer;
import com.udacity.jdnd.course3.critter.user.customer.model.CustomerDTO;
import com.udacity.jdnd.course3.critter.user.customer.model.CustomerDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    public CustomerRepository customerRepository;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer unsavedCustomer = CustomerDTOMapper.mapToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(unsavedCustomer);
        return CustomerDTOMapper.mapToCustomerDTO(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(customer -> CustomerDTOMapper.mapToCustomerDTO(customer))
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        Customer owner = customerRepository.findByPetsPetId(petId)
                .orElseThrow(EntityNotFoundException::new);
        CustomerDTO customerDTO = CustomerDTOMapper.mapToCustomerDTO(owner);
        return customerDTO;
    }
}
