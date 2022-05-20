package com.example.demo.services;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetService implements IPetService {

    @Autowired
    PetRepository repository;

    @Override
    public Pet getPetById(Long id) {
        log.info("Getting pet {}", id);
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Iterable<Pet> getAllPets() {
        log.info("Getting all pets");

        return this.repository.findAll();
    }

    @Override
    public Pet addPet(Pet pet) {
        log.info("Adding pet {}", pet);

        return this.repository.save(pet);
    }

    @Override
    public Pet updatePet(Long id, Pet pet) {
        Pet petDb = this.repository.findById(id).orElseThrow();
        petDb.setAge(pet.getAge());
        petDb.setIp(pet.getIp());
        petDb.setName(pet.getName());
        petDb.setSpecies(pet.getSpecies());
        petDb.setTag(pet.getTag());

        return this.repository.save(petDb);
    }

    @Override
    public Pet deletePet(Long id) {
        Pet petDb = this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);
        return petDb;
    }
    
}
