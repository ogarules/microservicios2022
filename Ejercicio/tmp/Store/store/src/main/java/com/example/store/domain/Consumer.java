package com.example.store.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Consumer {
    
    @Id
    private Long id;

    private String name;

    
}
