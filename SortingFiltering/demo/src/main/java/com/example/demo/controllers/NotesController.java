package com.example.demo.controllers;

import com.example.demo.models.Notes;
import com.example.demo.repositories.NotesRepository;
import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("notes")
public class NotesController {
    
    @Autowired
    NotesRepository repository;

    @GetMapping
    public Iterable<Notes> getAllNotes(@QuerydslPredicate(root = Notes.class) Predicate predicate,
            @SortDefault(sort = "noteText", direction = Direction.ASC)
            @PageableDefault(size = 5,page = 0) 
            Pageable page) {
        return this.repository.findAll(predicate, page);
    }
    
    @PostMapping
    public Notes addNote(@RequestBody Notes entity) {        
        return repository.save(entity);
    }
    
}
