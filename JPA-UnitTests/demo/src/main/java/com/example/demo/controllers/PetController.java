package com.example.demo.controllers;

import javax.validation.constraints.Min;
import javax.validation.groups.Default;

import com.example.demo.models.Pet;
import com.example.demo.services.IPetService;
import com.example.demo.validations.OnUpdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("/pets")
public class PetController {
    
    @Autowired
    private IPetService service;

    @Autowired
    SmartValidator validator;

    @GetMapping("/{id}")
    public Pet getPatById(@PathVariable Long id) {
        log.info("Requesting pet {}", id);

        return this.service.getPetById(id);
    }

    @GetMapping
    public Iterable<Pet> getAllPets() {
        log.info("Requesting all pets...");

        return this.service.getAllPets();
    }

    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet, BindingResult result) {

        this.validator.validate(pet, result);
        if (result.hasErrors()) {
            return new ResponseEntity<Pet>(pet, HttpStatus.BAD_REQUEST);
        }

        Pet petDb = this.service.addPet(pet);

        return ResponseEntity.ok(petDb);
    }

    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @Validated({ OnUpdate.class, Default.class }) @RequestBody Pet pet) {

        return this.service.updatePet(id, pet);
    }
    
    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable @Min(1) Long id) {
        this.service.deletePet(id);
    }
}
