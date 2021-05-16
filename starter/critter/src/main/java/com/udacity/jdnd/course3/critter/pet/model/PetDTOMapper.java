package com.udacity.jdnd.course3.critter.pet.model;

public class PetDTOMapper {
    public static Pet mapToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());
        pet.setNotes(petDTO.getNotes());
        pet.setType(petDTO.getType());
        return pet;
    }

    public static PetDTO mapToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getPetId());
        petDTO.setName(pet.getName());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());
        petDTO.setType(pet.getType());
        petDTO.setOwnerId(pet.getOwner().getUserId());
        return petDTO;
    }
}
