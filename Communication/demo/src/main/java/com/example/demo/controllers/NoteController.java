package com.example.demo.controllers;

import com.example.demo.models.Note;
import com.example.demo.services.ProducerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("notes")
public class NoteController {

    @Autowired
    ProducerService service;

    @PostMapping
    public Note postMethodName(@RequestBody Note entity) {
        this.service.publishNoteEvent(entity);
        
        return entity;
    }
    
}
