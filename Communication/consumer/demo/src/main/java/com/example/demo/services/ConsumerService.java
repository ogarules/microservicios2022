package com.example.demo.services;

import com.example.demo.models.Note;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConsumerService {
    
    @KafkaListener(topics = "notes", groupId = "group1")
    public void consume(Note note) {
        log.info("Consumed message => {}", note.getNoteText());
    }
}
