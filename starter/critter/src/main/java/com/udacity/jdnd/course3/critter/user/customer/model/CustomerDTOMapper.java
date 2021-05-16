package com.udacity.jdnd.course3.critter.user.customer.model;

import java.util.stream.Collectors;

public class CustomerDTOMapper {
    public static Customer mapToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        return customer;
    }

    public static CustomerDTO mapToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getUserId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());
        if (customer.getPets() != null) {
            customerDTO.setPetIds(customer.getPets().stream().map(pet -> pet.getPetId()).collect(Collectors.toList()));
        }
        return customerDTO;
    }
}
