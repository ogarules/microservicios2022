package com.example.store.controllers;

import com.example.store.domain.Consumer;
import com.example.store.repositories.ConsumerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("consumers")
public class ConsumerController {
    
    @Autowired
    ConsumerRepository repository;

    @GetMapping
    public Iterable<Consumer> getAllConsumers() {
        return this.repository.findAll();
    }
    

}
