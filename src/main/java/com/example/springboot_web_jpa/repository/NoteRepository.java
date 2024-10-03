package com.example.springboot_web_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot_web_jpa.model.Note;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByApplicationId(Long applicationId);
}