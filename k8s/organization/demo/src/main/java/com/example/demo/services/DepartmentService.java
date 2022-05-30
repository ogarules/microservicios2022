package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Department;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("department")
public interface DepartmentService {
    
    @GetMapping("/organization/{id}")
    public List<Department> getDepartmentsByOrganizationId(@PathVariable String id);

    @GetMapping("/organization/{id}/with-employees")
    public List<Department> getDepartmentsByOrganizationIdWithEmployees(@PathVariable String id);
}
