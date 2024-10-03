package com.example.springboot_web_jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.springboot_web_jpa.model.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT a FROM Application a LEFT JOIN FETCH a.notes")
    List<Application> findAllWithNotes();
}
