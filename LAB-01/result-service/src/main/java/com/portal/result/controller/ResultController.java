package com.portal.result.controller;

import com.portal.result.dto.ResultRequestDTO;
import com.portal.result.dto.ResultResponseDTO;
import com.portal.result.service.ResultService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {
    
    @Autowired
    private ResultService resultService;
    
    @PostMapping
    public ResponseEntity<?> createResult(@Valid @RequestBody ResultRequestDTO request) {
        try {
            ResultResponseDTO result = resultService.createResult(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create result: " + e.getMessage());
        }
    }
    
    @GetMapping("/student/{id}")
    public ResponseEntity<?> getResultsByStudent(@PathVariable Long id) {
        try {
            List<ResultResponseDTO> results = resultService.getResultsByStudentId(id);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to fetch results: " + e.getMessage());
        }
    }
}

