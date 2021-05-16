package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.pet.model.Pet;
import com.udacity.jdnd.course3.critter.pet.model.PetDTO;
import com.udacity.jdnd.course3.critter.pet.model.PetDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet unsavedPet = PetDTOMapper.mapToPet(petDTO);
        Pet savedPet = petService.savePet(unsavedPet, petDTO.getOwnerId());
        return PetDTOMapper.mapToPetDTO(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.findPetByPetId(petId);
        return PetDTOMapper.mapToPetDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> pets = petService.findAllPets();
        return pets.stream().map(pet -> PetDTOMapper.mapToPetDTO(pet)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findPetsByOwnerId(ownerId);
        return pets.stream().map(pet -> PetDTOMapper.mapToPetDTO(pet)).collect(Collectors.toList());
    }
}
