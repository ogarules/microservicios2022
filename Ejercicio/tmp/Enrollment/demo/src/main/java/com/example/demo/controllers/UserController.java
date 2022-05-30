package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("users")
public class UserController {
    
    @Autowired
    UserRepository repository;

    @Autowired
    ProducerService producer;

    @GetMapping
    public Iterable<User> getAllUsers() {
        return this.repository.findAll();
    }
    
    @GetMapping(value = "/{id}")
    public User getMethodName(@PathVariable Long id) {
        return this.repository.findById(id).orElseThrow();
    }
    

    @PostMapping
    public User postMethodName(@RequestBody User entity) {

        var result = this.repository.save(entity);

        this.producer.publishUserEvent(result);

        return result;
    }
    
    @PutMapping(value="/{id}")
    public User putMethodName(@PathVariable Long id, @RequestBody User entity) {
        var userDb = this.repository.findById(id).orElseThrow();

        userDb.setAddress(entity.getAddress());
        userDb.setEmali(entity.getEmali());
        userDb.setName(entity.getName());
        userDb.setPhone(entity.getPhone());

        var result = this.repository.save(userDb);

        this.producer.publishUserEvent(result);

        return result;
    }
}
