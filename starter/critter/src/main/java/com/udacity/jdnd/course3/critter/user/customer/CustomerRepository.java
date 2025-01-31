package com.udacity.jdnd.course3.critter.user.customer;

import com.udacity.jdnd.course3.critter.user.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPetsPetId(Long petId);
}
