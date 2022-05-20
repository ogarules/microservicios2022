package com.example.store.services;

import com.example.store.domain.Consumer;
import com.example.store.domain.User;
import com.example.store.repositories.ConsumerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerService {

    @Autowired
    ConsumerRepository repository;

    @KafkaListener(topics = "users", groupId = "group1")
    public void consume(User user) {
        log.info("Consumed message => {}", user.getName());

        var userDbOpt = this.repository.findById(user.getId());
        if (userDbOpt.isEmpty()) {
            Consumer consumer = new Consumer();
            consumer.setId(user.getId());
            consumer.setName(user.getName());

            this.repository.save(consumer);
        } else {
            var userDb = userDbOpt.get();
            userDb.setName(user.getName());

            this.repository.save(userDb);
        }

    }
}
