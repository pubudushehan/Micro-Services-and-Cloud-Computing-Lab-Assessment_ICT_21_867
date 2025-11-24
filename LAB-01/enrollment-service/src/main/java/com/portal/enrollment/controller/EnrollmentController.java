package com.portal.enrollment.controller;

import com.portal.enrollment.dto.EnrollmentRequestDTO;
import com.portal.enrollment.dto.EnrollmentResponseDTO;
import com.portal.enrollment.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class EnrollmentController {
    
    @Autowired
    private EnrollmentService enrollmentService;
    
    @PostMapping("/enroll")
    public ResponseEntity<?> enrollStudent(@Valid @RequestBody EnrollmentRequestDTO request) {
        try {
            EnrollmentResponseDTO enrollment = enrollmentService.enrollStudent(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(enrollment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Enrollment failed: " + e.getMessage());
        }
    }
    
    @GetMapping("/enrollments/student/{id}")
    public ResponseEntity<?> getEnrollmentsByStudent(@PathVariable Long id) {
        try {
            List<EnrollmentResponseDTO> enrollments = enrollmentService.getEnrollmentsByStudentId(id);
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to fetch enrollments: " + e.getMessage());
        }
    }
}

