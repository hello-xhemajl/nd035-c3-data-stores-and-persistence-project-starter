package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByOwnerUserId(Long ownerId);
}
