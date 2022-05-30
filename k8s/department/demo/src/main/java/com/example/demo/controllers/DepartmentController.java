package com.example.demo.controllers;

import java.util.List;

import com.example.demo.models.Department;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@Slf4j
public class DepartmentController {
    @Autowired
    DepartmentRepository repository;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        log.info("K8S Getting all departments...");
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable String id) {
        log.info("K8S get department {}", id);
        return this.repository.findById(id).orElseThrow();
    }
    
    @PostMapping
    public Department addDepartment(@RequestBody Department entity) {
        log.info("K8S Adding department...");

        return this.repository.save(entity);
    }
 
    @GetMapping("/organization/{id}")
    public List<Department> findByOrganizationId(@PathVariable String id) {
        log.info("K8S Getting departments from organization {}", id);

        return this.repository.findByOrganizationId(id);
    }

    @GetMapping("/organization/{id}/with-employees")
    public Iterable<Department> getMethodName(@PathVariable String id) {
        log.info("K8S getting departments with employees for organization {}", id);

        List<Department> departments = this.findByOrganizationId(id);
        departments.forEach(d -> d.setEmployee(this.employeeService.findByDepartmentId(d.getId())));

        return departments;
    }
    
}
