package com.example.store.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class User {
    
    private Long id;

    private String name;
    private String address;
    private String emali;
    private String phone;
}
