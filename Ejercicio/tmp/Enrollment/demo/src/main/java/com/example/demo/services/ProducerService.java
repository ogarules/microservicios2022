package com.example.demo.services;

import com.example.demo.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    
    @Autowired
    KafkaTemplate<String, User> kafkaTemplate;

    public void publishUserEvent(User note) {
        this.kafkaTemplate.send("users", note);
    }
}
