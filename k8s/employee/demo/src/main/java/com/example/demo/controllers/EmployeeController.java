package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Employee;
import com.example.demo.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeController {
    
    @Autowired
    EmployeeRepository repository;

    @GetMapping
    public Iterable<Employee> getAllEmployees() {
        log.info("K8S Getting all employees");
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable String id) {
        log.info("K8S Getting employee {}", id);
        return this.repository.findById(id).orElseThrow();
    }
    
    @PostMapping
    public Employee addEmployee(@RequestBody Employee entity) {
        log.info("K8S Adding employee....");

        return this.repository.save(entity);
    }
    
    @GetMapping("/department/{id}")
    public List<Employee> findEmployeeByDepartmentId(@PathVariable String id) {
        log.info("K8S Finding employees by department...");
        return this.repository.findByDepartmentId(id);
    }
    
    @GetMapping("/organization/{id}")
    public List<Employee> findEmployeeByOrganizationId(@PathVariable String id) {
        log.info("K8S Finding employees by organization...");
        return this.repository.findByOrganizationId(id);
    }
}
