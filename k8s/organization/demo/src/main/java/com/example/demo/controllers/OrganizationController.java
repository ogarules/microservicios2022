package com.example.demo.controllers;

import com.example.demo.models.Organization;
import com.example.demo.repositories.OrganizationRepository;
import com.example.demo.services.DepartmentService;
import com.example.demo.services.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@Slf4j
public class OrganizationController {
    
    @Autowired
    OrganizationRepository repository;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Iterable<Organization> getAllOrganizations() {
        log.info("K8S Getting all organizations...");
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable String id) {
        log.info("K8S Getting organization {}", id);
        return this.repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Organization addOrganization(@RequestBody Organization entity) {
        log.info("K8S adding organization...");

        return this.repository.save(entity);
    }
    
    @GetMapping("/{id}/departments")
    public Organization getOrganizationWithDepartments(@PathVariable String id) {
        log.info("K8S Getting organization with departments");

        Organization organization = this.repository.findById(id).orElseThrow();

        organization.setDepartments(this.departmentService.getDepartmentsByOrganizationId(id));

        return organization;
    }

    @GetMapping("/{id}/departmentsemployees")
    public Organization getOrganizationWithDepartmentsAndEmployeees(@PathVariable String id) {
        log.info("K8S Getting organization with departments and employees");

        Organization organization = this.repository.findById(id).orElseThrow();

        organization.setDepartments(this.departmentService.getDepartmentsByOrganizationIdWithEmployees(id));

        return organization;
    }

    @GetMapping("/{id}/employees")
    public Organization getOrganizationWithEmployees(@PathVariable String id) {
        log.info("K8S Getting organization with employees");

        Organization organization = this.repository.findById(id).orElseThrow();

        organization.setEmployees(this.employeeService.getEmployeesByOrganizationId(id));

        return organization;
    }

}
