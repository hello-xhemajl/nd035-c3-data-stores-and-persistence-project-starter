package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Pet savePet(Pet unsavedPet, Long ownerId) {
        Customer owner = customerRepository.findById(ownerId)
                .orElseThrow(EntityNotFoundException::new);

        unsavedPet.setOwner(owner);

        Pet pet = petRepository.save(unsavedPet);

        return pet;
    }

    public Pet findPetByPetId(Long petId) {
        return petRepository.findById(petId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> findPetsByOwnerId(long ownerId) {
        return petRepository.findByOwnerUserId(ownerId);
    }
}
