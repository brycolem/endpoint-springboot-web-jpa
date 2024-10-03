package com.example.springboot_web_jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springboot_web_jpa.model.Application;
import com.example.springboot_web_jpa.model.Note;
import com.example.springboot_web_jpa.service.ApplicationService;

import java.util.List;

@RestController
@RequestMapping("/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applications = applicationService.getAllApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Application> createApplication(@RequestBody Application application) {
        Application createdApplication = applicationService.createApplication(application);
        return new ResponseEntity<>(createdApplication, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplication(@PathVariable Long id) {
        Application application = applicationService.getApplication(id);
        return new ResponseEntity<>(application, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable Long id,
            @RequestBody Application updatedApplication) {
        Application updated = applicationService.updateApplication(id, updatedApplication);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Application> updatePartialApplication(@PathVariable Long id,
            @RequestBody Application partialUpdate) {
        Application updated = applicationService.updatePartialApplication(id, partialUpdate);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{applicationId}/notes")
    public ResponseEntity<Note> addNoteToApplication(@PathVariable Long applicationId, @RequestBody Note note) {
        Note createdNote = applicationService.addNoteToApplication(applicationId, note);
        return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
    }

    @GetMapping("/{applicationId}/notes")
    public ResponseEntity<List<Note>> getNotesByApplicationId(@PathVariable Long applicationId) {
        List<Note> notes = applicationService.getNotesByApplicationId(applicationId);
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }
}
