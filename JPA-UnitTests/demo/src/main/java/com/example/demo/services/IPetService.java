package com.example.demo.services;

import com.example.demo.models.Pet;

public interface IPetService {
    public Pet getPetById(Long id);

    public Iterable<Pet> getAllPets();

    public Pet addPet(Pet pet);

    public Pet updatePet(Long id, Pet pet);

    public Pet deletePet(Long id);
}
