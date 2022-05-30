package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("employee")
public interface EmployeeService {
    
    @GetMapping("/organization/{id}")
    public List<Employee> getEmployeesByOrganizationId(@PathVariable String id);
}
