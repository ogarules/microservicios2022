package com.example.store.repositories;

import com.example.store.domain.Consumer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumerRepository extends CrudRepository<Consumer,Long> {
    
}
