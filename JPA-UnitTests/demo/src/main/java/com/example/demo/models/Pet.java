package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.example.demo.validations.OnAdd;
import com.example.demo.validations.OnUpdate;

import lombok.Data;

@Data
@Entity
@Table(name="Pet")
public class Pet {
    
    @Null(groups = OnAdd.class)
    @NotNull(groups = OnUpdate.class)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tag;
    private String name;
    private String species;

    @Min(1)
    @Max(5)
    private Integer age;

    @Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String ip;
}
