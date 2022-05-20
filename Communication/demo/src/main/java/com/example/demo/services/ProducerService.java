package com.example.demo.services;

import com.example.demo.models.Note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    
    @Autowired
    KafkaTemplate<String, Note> kafkaTemplate;

    public void publishNoteEvent(Note note) {
        this.kafkaTemplate.send("notes", note);
    }
}
