package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Greeting;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "Greeting API" }, description = "API to manage greetings")
@RestController
@RequestMapping("/greeting")
public class HelloWorldController {
    
    @ApiOperation(value = "Get all greetings", produces = "application/json", consumes = "application/json", protocols = "http,https", notes = "For getting all greetings on the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "All greetings founds"),
            @ApiResponse(code = 404, message = "No greetings available")
    })
    @GetMapping
    public List<Greeting> getAllGreetings() {
        List<Greeting> greetings = new ArrayList<>();
        Greeting greeting = new Greeting();
        greeting.setMessage("Aloja !!!");
        greetings.add(greeting);

        return greetings;
    }
    
    @GetMapping("/{message}")
    public Greeting getGReeting(@PathVariable String message) {
        Greeting greeting = new Greeting();
        greeting.setMessage(message);

        return greeting;
    }
    
    @PostMapping
    public Greeting addGreeting(@RequestBody Greeting entity) {

        entity.setId(1L);

        return entity;
    }

    @PutMapping("/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestBody Greeting entity) {
        return entity;
    }

    @DeleteMapping("/{id}")
    public void deleteGReeting(@PathVariable Long id) {
        
    }
}
