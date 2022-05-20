package com.example.demo.repositories;

import com.example.demo.models.Notes;
import com.example.demo.models.QNotes;
import com.querydsl.core.types.dsl.StringPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository
        extends JpaRepository<Notes, Integer>, QuerydslPredicateExecutor<Notes>, QuerydslBinderCustomizer<QNotes> {
    
        @Override
        default void customize(QuerydslBindings bindings, QNotes root) {
            bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));    
            
        }
    
}
