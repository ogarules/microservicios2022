package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "employee")
public class Employee {
    
    @Id
    private String id;
    private String departmentId;
    private String organizationId;

    private String name;
    private int age;
    private String position;

}
