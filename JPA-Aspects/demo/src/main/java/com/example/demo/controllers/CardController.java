package com.example.demo.controllers;

import com.example.demo.models.Card;
import com.example.demo.services.ICardService;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/card")
public class CardController {
    
    @Autowired
    ICardService service;

    @GetMapping(value = "/{id}")
    public Card getCardById(@PathVariable Long id) {
        log.info("Requested card....");

        return this.service.getCardById(id);
    }
    
    @GetMapping
    public Iterable<Card> getAllCards() {
        log.info("Requesting all cards...");

        return this.service.getAllCards();
    }
    
    @PostMapping
    public Card addCard(@RequestBody Card entity) {
        log.info("Requesting card insert...");

        return this.service.addCard(entity);
    }

    @PutMapping(value="/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card entity) {
        log.info("Requesting card update....");

        return this.service.updateCard(id, entity);
    }
    
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        log.info("Request card delete");
        
        this.service.deleteCard(id);
    }
}
