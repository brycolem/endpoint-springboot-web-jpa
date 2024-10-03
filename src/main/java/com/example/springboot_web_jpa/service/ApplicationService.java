package com.example.springboot_web_jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.springboot_web_jpa.model.Application;
import com.example.springboot_web_jpa.model.Note;
import com.example.springboot_web_jpa.repository.ApplicationRepository;
import com.example.springboot_web_jpa.repository.NoteRepository;

import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final NoteRepository noteRepository;

    public ApplicationService(ApplicationRepository applicationRepository, NoteRepository noteRepository) {
        this.applicationRepository = applicationRepository;
        this.noteRepository = noteRepository;
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAllWithNotes();
    }

    public Application createApplication(Application application) {
        return applicationRepository.save(application);
    }

    public Application getApplication(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));
    }

    public Application updateApplication(Long id, Application updatedApplication) {
        Application existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        existingApplication.setEmployer(updatedApplication.getEmployer());
        existingApplication.setTitle(updatedApplication.getTitle());
        existingApplication.setLink(updatedApplication.getLink());
        existingApplication.setCompanyId(updatedApplication.getCompanyId());

        return applicationRepository.save(existingApplication);
    }

    public Application updatePartialApplication(Long id, Application partialUpdate) {
        Application existingApplication = applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        if (partialUpdate.getEmployer() != null) {
            existingApplication.setEmployer(partialUpdate.getEmployer());
        }
        if (partialUpdate.getTitle() != null) {
            existingApplication.setTitle(partialUpdate.getTitle());
        }
        if (partialUpdate.getLink() != null) {
            existingApplication.setLink(partialUpdate.getLink());
        }
        if (partialUpdate.getCompanyId() != null) {
            existingApplication.setCompanyId(partialUpdate.getCompanyId());
        }

        return applicationRepository.save(existingApplication);
    }

    public void deleteApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        applicationRepository.delete(application);
    }

    public Note addNoteToApplication(Long applicationId, Note note) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Application not found"));

        note.setApplication(application);
        return noteRepository.save(note);
    }

    public List<Note> getNotesByApplicationId(Long applicationId) {
        return noteRepository.findByApplicationId(applicationId);
    }
}